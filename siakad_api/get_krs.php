<?php
include 'config.php';
$nim = $_GET['nim'] ?? '';

// Mengambil data KRS berdasarkan NIM
$query = "SELECT * FROM krs WHERE nim='$nim'";
$result = mysqli_query($conn, $query);

$data = [];
while($row = mysqli_fetch_assoc($result)) {
    $data[] = $row;
}

echo json_encode($data);
?>