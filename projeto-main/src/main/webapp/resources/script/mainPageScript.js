const numAtual = document.getElementById("num_contrato");
const button = document.getElementById("num_contrato");

//dados do contrato
const screen1=document.getElementById("screen1");
//cadastro de contrato
const screen2=document.getElementById("screen2");
const showCadastroButton=document.getElementById("show-cadastro");

let numAtualAux = 0;


function showCadastro()
{
	if(!screen1.classList.contains("notShow")){
		screen1.classList.toggle("notShow");
	}
	screen2.classList.toggle("notShow");
}

function showForm()
{
	const newForm = document.getElementsByClassName("formNotFound")[0];
	const foundForm = document.getElementsByClassName("formFound")[0];
	const flag = document.getElementById("foundFormFlag")
	if(flag.innerText!="false")
	{
		foundForm.classList.toggle("notShow");
		newForm.classList.toggle("notShow");
	}
}

function getNum(clickFlag)
{
	const numAtual = document.getElementsByClassName("num_contrato")[0];
	const updateButton = document.getElementsByClassName("updateNumContrato")
	let numAtualAux = 0;
	console.log(numAtual.value);
	numAtualAux = this.innerText;
	numAtual.value = numAtualAux;
	if(clickFlag!=true){
		updateButton[0].click();
	}
}

const contrato = document.getElementsByClassName("contrato_id")
showCadastroButton.parentElement.addEventListener("click", showCadastro)
for(let i = 0;i<contrato.length;i++){
	contrato[i].parentElement.parentElement.addEventListener("click", getNum);
	//contrato[i].addEventListener("click", showForm);
}


//atualiza um campo invisível do numero do contrato pra resgitsrar no banco o form
//relacionado ao contrato
//lembrar que talvez a id mude de acordo com o jsf
const numOnForm =document.getElementsByClassName("num_contratoForm");
const num =document.getElementsByClassName("num_contrato")[0];
if(num.value=="")
{
	screen2.classList.remove("notShow");
	screen1.classList.toggle("notShow");
}
for(let i = 0;i<numOnForm.length;i++){
	numOnForm[i].value=num.value;
	numOnForm[i].classList.toggle("notShow");
}

//showForm();


