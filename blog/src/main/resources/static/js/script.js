// alert box for home page, uses sessionStorage for each session
if(sessionStorage.getItem('showAlert') != "false"){
    alert("Welcome to My Polish Blog. Enjoy your stay :)");
    sessionStorage.setItem('showAlert', "false");
}

// changes text color
function changeColor(element, color) {
    element.style.color = color;
}