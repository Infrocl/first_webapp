let letter = "";
let httpRequest;
const nameField = document.getElementById("sName");

nameField.addEventListener('keypress', function(event) {
    httpRequest = new XMLHttpRequest();
    if (!httpRequest) {
        alert("Cannot create an XMLHTTP instance");
        return false;
    }
    letter += event.key;
    httpRequest.onreadystatechange = alertContents;
    httpRequest.open("GET", "singer?action=autocomplete&letter=" + encodeURI(letter));
    httpRequest.send();
});

function alertContents() {
    if (httpRequest.readyState === XMLHttpRequest.DONE) {
        if (httpRequest.status === 200) {
            const singers = httpRequest.responseText.split('\n');
            autocomplete(singers);
        } else {
             alert("There was a problem with the request.");
        }
    }
}

function autocomplete(list) {
        closeList();
        suggestions = document.createElement('div');
        suggestions.setAttribute('id', 'suggestions');
        suggestions.setAttribute('class', 'autocomplete');
        nameField.parentNode.appendChild(suggestions);
        for (let i=0; i<list.length; i++) {
            suggestion = document.createElement('div');
            suggestion.innerHTML = list[i];
            suggestion.innerHTML += "<input type='hidden' value='" + list[i] + "'>";
            suggestion.addEventListener("click", function(e) {
                nameField.value = this.getElementsByTagName("input")[0].value;
                closeList();
            });
            suggestions.appendChild(suggestion);
        }
}
function closeList() {
        let suggestions = document.getElementById('suggestions');
        if (suggestions)
            suggestions.parentNode.removeChild(suggestions);
}

nameField.addEventListener('keydown', function(event) {
    if (event.key === 'Backspace') {
        httpRequest = new XMLHttpRequest();
          if (!httpRequest) {
                alert("Cannot create an XMLHTTP instance");
                return false;
          }
        let temp = letter.slice(0,-1);
        letter = temp;
        httpRequest.onreadystatechange = alertContents;
        httpRequest.open("GET", "singer?action=autocomplete&letter=" + encodeURI(letter));
        httpRequest.send();
    }
});