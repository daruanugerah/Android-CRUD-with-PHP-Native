<?php


// $connection = mysqli_connect("localhost","root","", "dbantrian") or die("Error " . mysqli_error($connection));


//    $connection = mysqli_connect("localhost","root","","dbpulsa") or die("Error " . mysqli_error($connection));


 include './config/koneksi.php';

    $sql = "SELECT * FROM tblkategorimakanan";

    $result = mysqli_query($connection, $sql) or die("Error in Selecting " . mysqli_error($connection));

    //create an array
    $emparray = array();
    while($row =mysqli_fetch_assoc($result))
    {
        $emparray[] = $row;
    }
   
    $akhir = array(
	
   	'DataKategori' => $emparray
    );

 	echo json_encode($akhir);


    //close the db connection
    mysqli_close($connection);
?>
