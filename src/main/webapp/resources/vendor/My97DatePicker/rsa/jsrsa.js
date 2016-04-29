var public_key="00e23af04997712e97c7d8694b6773399e9d26636068c3de464e8751fb58ed3d00a328bd4ac2249432b1a82a8fa3a4220d18b9de697a1c713ae1926c8f71df34d0e5f6c6d73e44f888dcb3ab129108109266fc53146906ea8f4ddf359e2d0d3321561d9b305dba07db44fbf76153447851d432a3e15a069ab171be249d4ff4384f";
var public_length="10001";
function setRSA(id)
{ var tmp = document.getElementById(id).value;
  //if(tmp.length==256 || id=='email' || id=='usermobile')
  if(tmp.length==256)
	{
		
	}else
	{
	  var rsa = new RSAKey();
	  rsa.setPublic(public_key, public_length);
	  var res = rsa.encrypt(document.getElementById(id).value); 
	  document.getElementById(id).value = res;
	  //alert(document.getElementById(id).value);
	}
}

function setRSA2(str)
{
  if(str.length>=256)
	{
	}else{
		var rsa = new RSAKey();
	    rsa.setPublic(public_key, public_length);
		var res = rsa.encrypt(str);  
		return res;
	}
}