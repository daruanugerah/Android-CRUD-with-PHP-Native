<?php 
include './config/koneksi.php';
   
	//importing dbDetails file 
	// define('HOST','localhost');
	// define('USER','root');
	// define('PASS','');
	// define('DB','mhspoli');
	
	// $con = mysqli_connect(HOST,USER,PASS,DB) or die('Unable to Connect');


	//this is our upload folder 
//	$upload_path = 'uploads/';
	
	//Getting the server ip 
//	$server_ip = gethostbyname(gethostname());
	
	//creating the upload url 
//	$upload_url = 'http://'.$server_ip.'/db_makanan/'.$upload_path; 
	
	//response array 
	$response = array(); 
	
	
	if($_SERVER['REQUEST_METHOD']=='POST'){
		
		//checking the required parameters from the request 
			if (isset($_POST["vsjenkel"]) && isset($_POST["vsnama"])  && isset($_POST["vsalamat"])  && isset($_POST["vsnotelp"])&& isset($_POST["vsusername"])&& isset($_POST["vslevel"])&& isset($_POST["vspassword"])) {
	
			//connecting to the database 
		//	$con = mysqli_connect(HOST,USER,PASS,DB) or die('Unable to Connect...');
			
		// 	$nama = 'andi';	
		// $alamat = 'padang';
		// $kota = 'jkt';
		// $notelp = '8222';
		// $username = 'sandi';
		// $level = 'admin';
		// $password = 'sandi11';
		// $jenkel = 'sds';
		
	//	$nim = $_POST['vsnim'];	
		$nama = $_POST['vsnama'];	
		$alamat = $_POST['vsalamat'];
		$jenkel = $_POST['vsjenkel'];
		$notelp = $_POST['vsnotelp'];
		$username = $_POST['vsusername'];
		$level = $_POST["vslevel"];
		$password = md5($_POST["vspassword"]);
// $id = $_POST["vspassword"];
	
			
			//trying to save the file in the directory 
			
			//Cek data sudah ada apa belum
 $sql = "SELECT * FROM tbluser WHERE username ='$username'";
   $check = mysqli_fetch_array(mysqli_query($connection,$sql));
   if(isset($check)){
     $response["result"] = "0";
     $response["msg"] = "oops! username sudah terdaftar!";
     echo json_encode($response);
   }else{
				//saving the file 
			//	move_uploaded_file($_FILES['image']['tmp_name'],$file_path);
				$sql = "INSERT INTO `tbluser` ( `nama`,`alamat`,`no_telp`,`jenkel`,`username`,`password`,`level`) VALUES ( '$nama', '$alamat', '$notelp', '$jenkel', '$username', '$password', '$level');";
				
				//adding the path and name to database 
				if(mysqli_query($connection,$sql)){
				//		echo 'Setting berhasil';
		
		$response['result'] = "1";
        $response['msg'] = "Berhasil register!!";
      //  $response['user'] = $nama;
     
					//filling response array with values 
					// $response['error'] = false; 
					// $response['url'] = $file_url; 
					// $response['name'] = $name;
				}else{
		//	echo 'Terjadi kesalahan, ulangi lagi!';
		   $response['result'] = "0";
      				$response['msg'] = "Gagal register!!";
	
		}
			//displaying the response 
			echo json_encode($response);
			
			//closing the connection 
		//	mysqli_close($connection);
		// }else{
		// 	$response['error']=true;
		// 	$response['message']='Please choose a file';
		 }
	}
	}
	/*
		We are generating the file name 
		so this method will return a file name for the image to be upload 
	*/
