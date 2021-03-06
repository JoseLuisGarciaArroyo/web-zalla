/* globals validarDni, validarNombre, validarFecha */
'use strict';

var formulario, // document.getElementById('formulario1');
    fechaActual, // new Date();
    errors; // listado de errores, se llena y vacía

window.onload = function()
{
    // Guardamos el formulario en una variable
    formulario = document.getElementById('formulario1');

    // Guardamos la fecha actual
    fechaActual = new Date();

    formulario.onsubmit = comprobarFormulario;
    formulario['btn-enviar'].onclick = createPopUp;

    formulario['btn-no-required'].onclick = limpiarCampos;
    formulario['btn-build-dates'].onclick = () =>
    {
        if (confirm('Esto añadirá las fechas numéricas a los selects. ¿Estás seguro?'))
        {
            rellenarFechas();
            formulario['btn-build-dates'].parentNode.removeChild(formulario['btn-build-dates']);
        }
    };
    formulario['btn-activate-wrong'].onclick = activarInputs;
    formulario['btn-activate-macarrada'].onclick = activarMacarrada;
};

function limpiarCampos()
{
    let limpiamos = confirm('Se borrarán todos los campos que obligan a introducir datos correctos. ¿Estás seguro?');

    if (limpiamos)
    {
        for (let input_ of formulario)
        {
            input_.required = false;

            if (input_.getAttribute('type') == 'number')
            {
                input_.removeAttribute('min');
                input_.removeAttribute('max');
                input_.setAttribute('type', 'text');
            }

            input_.removeAttribute('maxlength');
        }

        this.parentNode.removeChild(this);
    }
}

function rellenarFechas()
{
    let fechaActual = new Date();

    let dias = 31;
    let meses = ['enero', 'febrero', 'marzo', 'abril', 'mayo', 'junio', 'julio', 'agosto', 'septiembre', 'octubre', 'noviembre', 'diciembre'];
    let anos = [1910, fechaActual.getFullYear()];

    // Anadimos días
    let formDia = formulario['dia'];
    limpiarSelect(formDia);

    for (let i = 1; i <= dias; i++)
    {
        let newValue = (i > 9) ? i : '0' + i;
        let isSelected = (fechaActual.getDate() == i);

        formDia.add(new Option(i, newValue, isSelected, isSelected));
    }

    // Anadimos meses
    let formMes = formulario['mes'];
    limpiarSelect(formMes);
    formMes.style.width = '130px';

    for (let i = 0; i < meses.length; i++)
    {
        let newValue = ((i + 1) > 9) ? (i + 1) : '0' + (i + 1);
        let isSelected = (fechaActual.getMonth() == (i));

        formMes.add(new Option(meses[i], newValue, isSelected, isSelected));
    }

    // Anadimos anos
    let formAnos = formulario['anio'];
    limpiarSelect(formAnos);

    for (let i = anos[0]; i <= anos[1]; i++)
    {
        let isSelected = (i == anos[1]);

        formAnos.add(new Option(i, i, isSelected, isSelected));
    }

}

function limpiarSelect(selectGroup_)
{
    for (var i = selectGroup_.options.length; i >= 0; i--)
    {
        selectGroup_.options[i] = null;
    }
}

function activarInputs()
{

    if (confirm('Esto activará la corrección automática de los bloques. También hará que el Array de errores se llene de mierda hasta que hagas un submit. ¿Estás seguro de hacerlo?'))
    {
        errors = new Array();

        formulario['nombre'].onchange = comprobarNombre;
        formulario['apellidos'].onchange = comprobarApellidos;
        formulario['dni'].onchange = comprobarDNI;
        formulario['edad'].onchange = comprobarEdad;
        formulario['dia'].onchange = comprobarFecha;
        formulario['mes'].onchange = comprobarFecha;
        formulario['anio'].onchange = comprobarFecha;

        this.parentNode.removeChild(this);
    }
}

function comprobarFormulario()
{
    // Guardamos los errores en un array
    errors = new Array();

    comprobarNombre();
    comprobarApellidos();
    comprobarDNI();
    comprobarEdad();
    comprobarFecha();
    comprobarIdioma();


    if (errors.length == 0)
    {
        alert('Todo correcto, puedes continuar');
    }
    else
    {
        let errorMSG = '¡ERROR!¡ERROR!¡ERROR!¡ERROR!\n\nTienes ' + errors.length + ' errores.\n\n';

        for (let error_ of errors)
        {
            errorMSG += '  ->  ' + error_ + '\n';
        }

        alert(errorMSG);
        return false;
    }

}

function comprobarNombre(event_ = 'doError')
{
    let nombre = formulario['nombre'].value;

    let errMsg = 'El nombre introducido no es correcto';
    let condition = nombre == '';

    if (event_ == 'doError')
    {
        doError(condition, formulario['nombre'], errMsg);
    }
    else if (event_ == 'activateSide')
    {
        return (condition) ? errMsg : false;
    }
}

function comprobarApellidos(event_ = 'doError')
{
    let apellidos = formulario['apellidos'].value;

    let errMsg = 'Los apellidos introducidos no son correctos';
    let condition = apellidos == '';

    if (event_ == 'doError')
    {
        doError(condition, formulario['apellidos'], errMsg);
    }
    else if (event_ == 'activateSide')
    {
        return (condition) ? errMsg : false;
    }
}

function comprobarDNI(event_ = 'doError')
{
    let dni = formulario['dni'].value;

    let errMsg = 'El dni introducido no es correcto';
    let condition = (dni == '' || !validarDni(dni));

    if (event_ == 'doError')
    {
        doError(condition, formulario['dni'], errMsg);
    }
    else if (event_ == 'activateSide')
    {
        return (condition) ? errMsg : false;
    }
}

function comprobarEdad(event_ = 'doError')
{
    const EDAD_MINIMA = 1;
    const EDAD_MAXIMA = 120;

    let edad = parseInt(formulario['edad'].value, 10);

    let errMsg = 'La edad introducida no es válida';
    let condition = (isNaN(edad) || edad < EDAD_MINIMA || edad > EDAD_MAXIMA);

    if (event_ == 'doError')
    {
        doError(condition, formulario['edad'], errMsg);
    }
}

function comprobarFecha(event_ = 'doError')
{
    let dia = parseInt(formulario['dia'].value, 10),
        mes = parseInt(formulario['mes'].value, 10),
        ano = parseInt(formulario['anio'].value, 10);

    // Si la condición se cumple manda el error. :S no es muy inteligente la verdad :(
    let condition = true;
    let errMsg = 'La fecha introducida no es válida';

    let isValid = (validarFecha(dia, mes - 1, ano));
    let bloque = document.getElementById('grupo_fechas');

    if (isValid)
    {
        let fechaSeleccionada = new Date(ano, mes - 1, dia);

        if (fechaSeleccionada.getTime() < Date.now())
        {
            condition = false;
        }
    }

    if (event_ == 'doError')
    {
        doError(condition, bloque, errMsg);
    }
    else if (event_ == 'activateSide')
    {
        return (condition) ? errMsg : false;
    }
}

function comprobarIdioma(event_ = 'doError')
{
    let isLanguage = false;
    for (let i = 1; i <= 3; i++)
    {
        if (document.getElementById('idioma' + i).checked)
        {
            isLanguage = true;
        }
    }

    let bloque = document.getElementById('grupo-idiomas');

    let errMsg = 'Selecciona al menos un idioma';
    let condition = !isLanguage;


    if (event_ == 'doError')
    {
        doError(!isLanguage, bloque, 'Selecciona al menos un idioma');
    }
    else if (event_ == 'activateSide')
    {
        return (condition) ? errMsg : false;
    }
}


function doError(condition_, block_, msg_ = 'Ha habido un error desconocido')
{
    if (condition_)
    {
        errors.push(msg_);
        block_.className = 'wrong';
    }
    else
    {
        block_.className = 'right';
    }
}

function createPopUp()
{
    errors = new Array();

    comprobarNombre();
    comprobarApellidos();
    comprobarDNI();
    comprobarEdad();
    comprobarFecha();
    comprobarIdioma();

    let divFondo = document.createElement('div');

    divFondo.className = 'modal';
    document.body.appendChild(divFondo);

    let divMensaje = document.createElement('div');

    divMensaje.className = 'internal';
    divFondo.appendChild(divMensaje);

    let finalBtn = document.createElement('button');

    if (errors.length == 0)
    {
        divMensaje.className = divMensaje.className + ' right';

        divMensaje.innerHTML = '<h1>Todo Correcto</h1>';

        finalBtn.innerText = 'Continuar';
        divMensaje.appendChild(finalBtn);

        finalBtn.onclick = () =>
        {
            formulario.submit();
        };
    }
    else
    {
        divMensaje.className = divMensaje.className + ' wrong';

        let errorMSG = '<h1>¡ERROR!¡ERROR!¡ERROR!¡ERROR!</h1><p>Tienes ' + errors.length + ' errores.</p><ul>';

        for (let error_ of errors)
        {
            errorMSG += '<li>' + error_ + '</li>';
        }

        errorMSG += '</ul>';

        divMensaje.innerHTML = errorMSG;

        finalBtn.innerText = 'Cerrar';
        divMensaje.appendChild(finalBtn);

        finalBtn.onclick = () =>
        {
            divFondo.parentNode.removeChild(divFondo);
        };
    }
}


function activarMacarrada()
{
    let contenedor = document.getElementById('contenedor');

    contenedor.className = 'rotation';

    contenedor.addEventListener("animationend", limpiarGiro, false);

}

function limpiarGiro()
{
    console.log('control de Listeners');
    this.className = '';
    this.removeEventListener("animationend", limpiarGiro);
}