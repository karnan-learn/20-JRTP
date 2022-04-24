var petName = "dog";
function setPetName() {
    var petName = "cat";
    console.log("Inside Function :: " + petName);
}
setPetName();
console.log("Outside Function :: " + petName);
