function customerRequest(parameters) {
    let country = document.getElementById("countries").value;
    let url = "CustomerController";

    console.log("customerRequest: trying to get info for country " + country);

    fetch(url, {
        method: 'post',
        body: country
    }).then((response) => {
        console.log("fetch2");
        response.text().then(result => {
            console.log("result=" + result);
            document.getElementById("representatives").innerHTML = result;
        }
     );
    });
}