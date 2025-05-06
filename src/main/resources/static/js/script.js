function carregarPresencas() {
    $.ajax({
        url: '/presenca/',
        type: 'GET',
        success: function (presencas) {
            let lista = '';
            presencas.forEach(p => {
                lista += `
                    <li class="list-group-item">
                        <strong>${p.nome}</strong><br>
                        <small>${p.cargo} - ${p.empresa}</small>
                    </li>
                `;
            });
            $('#listaPresencas').html(lista);
        }
    });
}

function adicionarPresenca(e) {
    const presenca = {
        nome: $('#nome').val(),
        cargo: $('#cargo').val(),
        empresa: $('#empresa').val()
    };

    $.ajax({
        url: '/presenca/',
        type: 'POST',
        contentType: 'application/json',
        data: JSON.stringify(presenca),
        success: function () {
            $('#formPresenca')[0].reset();
            carregarPresencas();
        }
    });
}

$(document).ready(function () {
    carregarPresencas();
    $('#formPresenca').submit(adicionarPresenca);
});
// Arquivo de script vazio 
