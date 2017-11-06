var loginapp = angular.module('loginApp', []);

loginapp.controller('loginController', function($scope, $http, $location, $window, $rootScope) {

	$scope.loginUserFunc = function() {
		
		var url = "/loginuser";
		var landingUrl = "http://" + $window.location.host + "/homepage.html";
		
		//alert(url);
		//alert(landingUrl);
		
		
		var payload = {"userName": $scope.uName, "password": $scope.pword};
		var jsonPayload = JSON.stringify(payload);
		
		var authdata = Base64.encode($scope.uName + ':' + $scope.pword);
		
		//alert(authdata);
		
		$rootScope.globals = {
                currentUser: {
                    username: $scope.uName,
                    authdata: $scope.pword
                }
            };
		
		//alert(payload);
		
		$http.post(url, jsonPayload)
			.then(function (response){
				
				//console.log(response.data.responseCode);
				//alert(JSON.stringify(response.data));
				if(response.data.responseCode == 1) {
					
					//$http.defaults.headers.common['Authorization'] = 'Basic ' + authdata; 
		            //$cookieStore.put('globals', $rootScope.globals);
					$window.location.href = landingUrl;
				} else {
					//alert($scope.errorMsg);
					$scope.errorMsg = "Invalid Username or Password";
					$scope.errorSwitch = true;
					//alert($scope.errorMsg);
				}
				
			}, function (response) {
				if(response.status == 403) {
					
					$scope.errorMsg = "Unauthorized access";
					$scope.errorSwitch = true;
				}
			});
	}
	
	/*service.ClearCredentials = function () {
        $rootScope.globals = {};
        $cookieStore.remove('globals');
        $http.defaults.headers.common.Authorization = 'Basic ';
    };*/
	
	
	$scope.closeErrorMessage = function() {
		$scope.errorMsg = "";
		$scope.errorSwitch = false;
	}
});

var homepageapp = angular.module('homepageApp', []);

homepageapp.controller('homepageController', function ($scope, $http, $window, $location, $timeout, $filter) {
	
	$scope.todayTasks=[];
	var today = new Date();
	$scope.date = today;
	var landingUrl = "http://" + $window.location.host + "/index.html";
	
	$scope.logout = function() {
		
		$http.get("/logout")
		.then(function (response){
			
			//console.log(response.data.responseCode);
			
			if(response.data.responseCode == 1) {
				
				//$http.defaults.headers.common['Authorization'] = 'Basic ' + authdata; 
	            //$cookieStore.put('globals', $rootScope.globals);
				$window.location.href = landingUrl;
			} /*else {
				//alert($scope.errorMsg);
				$scope.errorMsg = "Invalid Username or Password";
				$scope.errorSwitch = true;
				//alert($scope.errorMsg);
			}*/
			
		}, function (response) {
			if(response.status == 403) {
				
				$scope.errorMsg = "Unauthorized access";
				$scope.errorSwitch = true;
			}
		});
	}
	
	$scope.addTask = function() {
	
		var taskAddPayload = {"taskDesc": $scope.taskdesc, "priority": $scope.priority, "scheduleDate" : $scope.scheduledate/*, "isFollowupRequired": $scope.followupreq*/};
		var taskAddJson = JSON.stringify(taskAddPayload);
		
		//alert(taskAddJson);
		
		$http.post("/addtask", taskAddJson)
		.then(function (response){
			
			//console.log(response.data.responseCode);
			
			if(response.data.responseCode == 1) {
				
				$scope.errorMsg = "Task created";
				$scope.errorSwitch = true;
				//alert($filter('date')(response.data.responseData.scheduleDate, "dd-MM-yyyy"));
				//alert($filter('date')(today, "dd-MM-yyyy"));
				//alert($filter('date')(response.data.responseData.scheduleDate, "dd-MM-yyyy") == $filter('date')(today, "dd-MM-yyyy"));
				if($filter('date')(response.data.responseData.scheduleDate, "dd-MM-yyyy") == $filter('date')(today, "dd-MM-yyyy")) {
					var newTask = {};
					
					newTask.taskID = response.data.responseData.taskID;
					newTask.taskDesc = response.data.responseData.taskDesc;
					newTask.priority = response.data.responseData.priority;
					newTask.scheduleDate = response.data.responseData.scheduleDate;
					newTask.userID = response.data.responseData.userID;
					
					$scope.todayTasks.push(newTask);
				}
				
				$timeout(function() {
					$scope.errorMsg = "";
					$scope.errorSwitch = false;
				}, 1000);
			} else {
				$scope.errorMsg = "Task could not be created";
				$scope.errorSwitch = true;
				

				$timeout(function() {
					//alert("time to close" + $scope.errorSwitch);
					$scope.errorMsg = "";
					$scope.errorSwitch = false;
					//alert("closed" + $scope.errorSwitch);
				}, 1000);
			}
			
		}, function (response) {
			
			$scope.errorMsg = "Task could not be created";
			$scope.errorSwitch = true;
			

			$timeout(function() {
				$scope.errorMsg = "";
				$scope.errorSwitch = false;
			}, 1000);
		});
	}
	
	$scope.todayTasks=[];
	
	$http.get("gettodaytask")
	.then(function(response) {
		
		if(response.data.responseCode == 1) {
			
			for(var i=0; i< response.data.responseData.length; i++) {
				var newTask = {};
				
				newTask.taskID = response.data.responseData[i].taskID;
				newTask.taskDesc = response.data.responseData[i].taskDesc;
				newTask.priority = response.data.responseData[i].priority;
				newTask.scheduleDate = response.data.responseData[i].scheduleDate;
				newTask.userID = response.data.responseData[i].userID;
				//alert(newTask);
				$scope.todayTasks.push(newTask);
			}

		}
	});
	
	$scope.closeErrorMessage = function() {
		
		$scope.errorMsg = "";
		$scope.errorSwitch = false;
	}
});

var Base64 = {
	    characters: "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=" ,

	    encode: function( string )
	    {
	        var characters = Base64.characters;
	        var result     = '';

	        var i = 0;
	        do {
	            var a = string.charCodeAt(i++);
	            var b = string.charCodeAt(i++);
	            var c = string.charCodeAt(i++);

	            a = a ? a : 0;
	            b = b ? b : 0;
	            c = c ? c : 0;

	            var b1 = ( a >> 2 ) & 0x3F;
	            var b2 = ( ( a & 0x3 ) << 4 ) | ( ( b >> 4 ) & 0xF );
	            var b3 = ( ( b & 0xF ) << 2 ) | ( ( c >> 6 ) & 0x3 );
	            var b4 = c & 0x3F;

	            if( ! b ) {
	                b3 = b4 = 64;
	            } else if( ! c ) {
	                b4 = 64;
	            }

	            result += Base64.characters.charAt( b1 ) + Base64.characters.charAt( b2 ) + Base64.characters.charAt( b3 ) + Base64.characters.charAt( b4 );

	        } while ( i < string.length );

	        return result;
	    } ,

	    decode: function( string )
	    {
	        var characters = Base64.characters;
	        var result     = '';

	        var i = 0;
	        do {
	            var b1 = Base64.characters.indexOf( string.charAt(i++) );
	            var b2 = Base64.characters.indexOf( string.charAt(i++) );
	            var b3 = Base64.characters.indexOf( string.charAt(i++) );
	            var b4 = Base64.characters.indexOf( string.charAt(i++) );

	            var a = ( ( b1 & 0x3F ) << 2 ) | ( ( b2 >> 4 ) & 0x3 );
	            var b = ( ( b2 & 0xF  ) << 4 ) | ( ( b3 >> 2 ) & 0xF );
	            var c = ( ( b3 & 0x3  ) << 6 ) | ( b4 & 0x3F );

	            result += String.fromCharCode(a) + (b?String.fromCharCode(b):'') + (c?String.fromCharCode(c):'');

	        } while( i < string.length );

	        return result;
	    }
	};