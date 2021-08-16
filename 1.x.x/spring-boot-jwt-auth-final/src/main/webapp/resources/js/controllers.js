var app = angular.module('statelessApp', []).factory('TokenStorage', function() {
	var storageKey = 'javatab_auth_token';
	return {
		store : function(token) {
			return localStorage.setItem(storageKey, token);
		},
		retrieve : function() {
			return localStorage.getItem(storageKey);
		},
		clear : function() {
			return localStorage.removeItem(storageKey);
		}
	};
}).factory('TokenAuthInterceptor', function($q, TokenStorage) {
	return {
		request: function(config) {
			var authToken = TokenStorage.retrieve();
			if (authToken) {
				config.headers['JAVATAB-AUTH-TOKEN'] = authToken;
			}
			return config;
		},
		responseError: function(error) {
			if (error.status === 401 || error.status === 403) {
				TokenStorage.clear();
			}
			return $q.reject(error);
		}
	};
}).config(function($httpProvider) {
	$httpProvider.interceptors.push('TokenAuthInterceptor');
});

app.controller('AuthCtrl', function ($scope, $http, TokenStorage) {
	$scope.authenticated = false;
	$scope.token; // For display purposes only

    $scope.userDetails = {} //For displaying user details

	$scope.init = function () {
		$http.get('/api/users/current').success(function (user) {
			if(user.username !== 'anonymousUser'){
				$scope.authenticated = true;
				$scope.username = user.username;

				// For display purposes only
				$scope.token = JSON.parse(atob(TokenStorage.retrieve().split('.')[0]));
			}
		});
	};

	$scope.login = function () {
		$http.post('/api/login', { username: $scope.username, password: $scope.password }).success(function (result, status, headers) {
			$scope.authenticated = true;
			TokenStorage.store(headers('JAVATAB-AUTH-TOKEN'));

			// For display purposes only
			$scope.token = JSON.parse(atob(TokenStorage.retrieve().split('.')[0]));
		});
	};

	$scope.logout = function () {
		// Just clear the local storage
		TokenStorage.clear();
		$scope.authenticated = false;
	};

	$scope.getCurrentUserDetails = function () {
        $http.get('/api/users/' + $scope.username).success(function (user) {

            $scope.userDetails.id = user.id;
            $scope.userDetails.name = user.username;

        });
	}
});
