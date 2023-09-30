function verify() {
    var password = document.forms['form']['password'].value;
    var userName = document.forms['form']['userName'].value;

    if (password === null || password === '') {
        document.getElementById("error").innerHTML = "Password is required";
        return false;
    }

    if (userName === null || userName === '') {
        document.getElementById("error").innerHTML = "User name is required";
        return false;
    }

    var checkboxes = document.getElementsByName("authorities");
    var okay = false;

    for (var i = 0; i < checkboxes.length; i++) {
        if (checkboxes[i].checked) {
            okay = true;
            break;
        }
         document.getElementById("error").innerHTML = "You must select at least one role";
        return false;
    
    }      

    return okay;
}
