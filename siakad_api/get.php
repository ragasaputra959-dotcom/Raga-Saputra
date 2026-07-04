<?php
include 'config.php';

// Memastikan NIM dikirim, jika tidak, berikan respon kosong atau error
$nim = isset($_GET['nim']) ? $_GET['nim'] : '';

if (!empty($nim)) {
    // Menambahkan header JSON agar Postman dan Android membacanya sebagai data JSON
    header('Content-Type: application/json');
    
    $query = "SELECT * FROM khs WHERE nim = '$nim'";
    $result = mysqli_query($conn, $query);
    
    $data = [];
    while ($row = mysqli_fetch_assoc($result)) {
        $data[] = $row;
    }
    
    echo json_encode($data);
} else {
    echo json_encode(["status" => "error", "message" => "NIM tidak ditemukan"]);
}
?>