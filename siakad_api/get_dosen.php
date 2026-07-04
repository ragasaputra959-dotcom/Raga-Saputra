<?php
include 'config.php';

// Menampilkan daftar semua dosen
$query = "SELECT * FROM dosen";
$result = mysqli_query($conn, $query);

$data = [];
while($row = mysqli_fetch_assoc($result)) {
    $data[] = $row;
}

echo json_encode($data);
?>