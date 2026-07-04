<?php
include 'config.php';
$nim = $_GET['nim'] ?? '';

// Mengambil data KHS (Nilai) berdasarkan NIM
$query = "SELECT * FROM khs WHERE nim='$nim'";
$result = mysqli_query($conn, $query);

$data = [];
while($row = mysqli_fetch_assoc($result)) {
    $data[] = $row;
}

echo json_encode($data);
?>