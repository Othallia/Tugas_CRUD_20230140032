$(document).ready(function() {
    loadKtp(); 

    $('#ktpForm').on('submit', function(e) {
        e.preventDefault();
        let id = $('#ktpId').val();
        let url = id ? '/ktp/' + id : '/ktp';
        let method = id ? 'PUT' : 'POST';

        let data = {
            nomorKtp: $('#nomorKtp').val(),
            namaLengkap: $('#namaLengkap').val(),
            alamat: $('#alamat').val(),
            tanggalLahir: $('#tanggalLahir').val(),
            jenisKelamin: $('#jenisKelamin').val()
        };

        $.ajax({
            url: url,
            method: method,
            contentType: 'application/json',
            data: JSON.stringify(data),
            success: function(response) {
                alert('Berhasil menyimpan data!');
                resetForm();
                loadKtp();
            },
            error: function(err) {
                alert('Gagal menyimpan data!');
            }
        });
    });
});

function loadKtp() {
    $.get('/ktp', function(response) {
        let rows = '';
        response.data.forEach(ktp => {
            rows += `<tr>
                <td>${ktp.nomorKtp}</td>
                <td>${ktp.namaLengkap}</td>
                <td>${ktp.alamat}</td>
                <td>${ktp.tanggalLahir}</td>
                <td>${ktp.jenisKelamin}</td>
                <td>
                    <button class="btn btn-warning btn-sm" onclick="editKtp(${ktp.id})">Edit</button>
                    <button class="btn btn-danger btn-sm" onclick="deleteKtp(${ktp.id})">Hapus</button>
                </td>
            </tr>`;
        });
        $('#ktpTableBody').html(rows);
    });
}

function editKtp(id) {
    $.get('/ktp/' + id, function(response) {
        let ktp = response.data;
        $('#ktpId').val(ktp.id);
        $('#nomorKtp').val(ktp.nomorKtp);
        $('#namaLengkap').val(ktp.namaLengkap);
        $('#alamat').val(ktp.alamat);
        $('#tanggalLahir').val(ktp.tanggalLahir);
        $('#jenisKelamin').val(ktp.jenisKelamin);
        
        $('#btnSave').text('Update Data');
        $('#formTitle').text('Edit Data KTP');
        $('#btnCancel').removeClass('d-none');
    });
}

function deleteKtp(id) {
    if (confirm('Yakin ingin menghapus?')) {
        $.ajax({
            url: '/ktp/' + id,
            method: 'DELETE',
            success: function() {
                alert('Terhapus!');
                loadKtp();
            }
        });
    }
}

function resetForm() {
    $('#ktpForm')[0].reset();
    $('#ktpId').val('');
    $('#btnSave').text('Simpan Data');
    $('#formTitle').text('Tambah Data KTP');
    $('#btnCancel').addClass('d-none');
}