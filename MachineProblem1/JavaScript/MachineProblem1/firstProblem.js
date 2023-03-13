let number;

//Function for checking if 2 arrays are equal
function arrayEquals(a, b) {
    return Array.isArray(a) &&
        Array.isArray(b) &&
        a.length === b.length &&
        a.every((val, index) => val === b[index]);
}

document.getElementById("Button").onclick = function() {
    let list = [];
    number = document.getElementById("inputNumber").value;
    if (Number.isInteger(parseInt(number)) === true) {
        let result = (n,list) => {
            
            //Checker if the last 3 values is repeating for 2nd time
            if (list.length >= 6 ){
                newList = list.slice(-6);
                last = newList.slice(-3);
                prev = newList.slice(0,3);
                if(arrayEquals(prev,last) === true){
                    return list;
                }
            }
        
            if (n === 0) {;
                return;
            }
            else if (n % 2 === 0) {
                list.push(parseInt(n));
                return result(parseInt(n/2),list);
            }
            else {
                list.push(parseInt(n));
                return result(3 * n + 1, list);
            }
        };
        
        result(number,list);
        console.log(list);
    }
    else {
        console.log("Invalid Input");
    }
};



