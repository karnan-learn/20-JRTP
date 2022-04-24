function greet(msg) {
    var names = [];
    for (var _i = 1; _i < arguments.length; _i++) {
        names[_i - 1] = arguments[_i];
    }
    return msg + " " + names.join(",");
}
console.log(greet('Hi', 'Ashok', 'IT')); //OK
console.log(greet('Hello')); //Ok
function add(a, b) {
    return a + b;
}
add(10, 20);
add("Ashok", "IT");
