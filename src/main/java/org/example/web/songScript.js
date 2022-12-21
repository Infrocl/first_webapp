let ch = "";
let num = "";
let flag = 0;
let httpRequest;
const nameField = document.getElementById("sgName");
const idField = document.getElementById("aId");
nameField.addEventListener('keypress', keyEventHandler);
nameField.addEventListener('keydown', backspaceHandler);
idField.addEventListener('keypress', keyEventHandler);
idField.addEventListener('keydown', backspaceHandler);

function validate() {
    const duration = document.getElementById("duration").value;
    if (/^(2[0-3]|[01][0-9]):([0-5][0-9]):([0-5][0-9])$/.test(duration)) {
        return true;
    } else {
        alert("Please, enter the duration less than 24 hours as 00:00:00!");
        return false;
    }
}

function keyEventHandler(event) {
    httpRequest = new XMLHttpRequest();
    if (!httpRequest) {
        alert("Cannot create an XMLHTTP instance");
        return false;
    }
    if (this === nameField) {
        flag = 0;
        ch += event.key;
        httpRequest.open("GET", "song?action=autocomplete&type=0&letter=" + encodeURI(ch));
    } else {
        flag = 1;
        num += event.key;
        httpRequest.open("GET", "song?action=autocomplete&type=1&letter=" + encodeURI(num));
      }
     httpRequest.onreadystatechange = alertContents;
     httpRequest.send();
}

function alertContents() {
    if (httpRequest.readyState === XMLHttpRequest.DONE) {
        if (httpRequest.status === 200) {
            const result = httpRequest.responseText.split('\n');
            if (flag === 0)
                autocomplete(result, nameField);
            else
                autocomplete(result, idField);
        } else {
             alert("There was a problem with the request.");
        }
    }
}
function autocomplete(list, input) {
        closeList();
        suggestions = document.createElement('div');
        suggestions.setAttribute('id', 'suggestions');
        suggestions.setAttribute('class', 'autocomplete');
        input.parentNode.appendChild(suggestions);
        for (let i=0; i<list.length; i++) {
            suggestion = document.createElement('div');
            suggestion.innerHTML = list[i];
            suggestion.innerHTML += "<input type='hidden' value='" + list[i] + "'>";
            suggestion.addEventListener("click", function(e) {
                if (flag == 0)
                    input.value = this.getElementsByTagName("input")[0].value;
                else
                    input.value = +this.getElementsByTagName("input")[0].value;
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
function backspaceHandler(event) {
    if (event.key === 'Backspace') {
        httpRequest = new XMLHttpRequest();
        if (!httpRequest) {
             alert("Cannot create an XMLHTTP instance");
             return false;
        }
        if (this === nameField) {
             flag = 0;
             let temp = ch.slice(0,-1);
             ch = temp;
             httpRequest.open("GET", "song?action=autocomplete&type=0&letter=" + encodeURI(ch));
        } else {
             flag = 1;
             let temp = num.slice(0,-1);
             num = temp;
             httpRequest.open("GET", "song?action=autocomplete&type=1&letter=" + encodeURI(num));
        }
        httpRequest.onreadystatechange = alertContents;
        httpRequest.send();
    }
}