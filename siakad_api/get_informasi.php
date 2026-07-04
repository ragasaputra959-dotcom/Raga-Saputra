<?php
include 'config.php';

// Informasi bersifat umum, tampilkan semua tanpa filter NIM
$query = "SELECT * FROM informasi";
$result = mysqli_query($conn, $query);

$data = [];
while($row = mysqli_fetch_assoc($result)) {
    $data[] = $row;
}

echo json_encode($data);
?>