let btnChangeFirstName = document.querySelector("#btnChangeFirstName");
let btnValidChangeFirstName = document.querySelector("#btnValidChangeFirstName");
let divFirstName = document.querySelector("#firstName");
let divchangeFirstName = document.querySelector("#changeFirstName");

btnChangeFirstName.addEventListener("click", function(){
  divFirstName.classList.add("d-none");
  divchangeFirstName.classList.remove("d-none");
})
