var path = "http://localhost:8080/TecWebSoap/webresources/service/";
console.log("entraste javascript");

function getTabla() {
    $.get(path + "getAllRegion", function(data) {
        if (data !== null && data !== '') {
            $('#myTable').empty();
            $('#myTable').append('<tr><th>#</th><th>ID</th><th>Nombre</th></tr>');
            $.each(data, function(index, v) {                
                $('#myTable').append('<tr><td>' + (index + 1) + '</td><td>' + v.idRegion + '</td><td>' + v.regionName + '</td></tr>');
            });
        }
    });
}

function buscar() {
    var idRegion = $('#idRegion').val();        
    $.get(path + "getRegion?idRegion=" + idRegion, function(data) {        
        if (data !== null && data !== '') {
            $('#regionName').val(data.regionName);
        }        
    });
}