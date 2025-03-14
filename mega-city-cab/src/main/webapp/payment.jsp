<%@ page
	import="java.text.SimpleDateFormat, java.util.Date, java.util.concurrent.TimeUnit"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
<title>Carbook - Free Bootstrap 4 Template by Colorlib</title>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<link
	href="https://fonts.googleapis.com/css?family=Poppins:200,300,400,500,600,700,800&display=swap"
	rel="stylesheet">

<link rel="stylesheet" href="css/open-iconic-bootstrap.min.css">
<link rel="stylesheet" href="css/animate.css">

<link rel="stylesheet" href="css/owl.carousel.min.css">
<link rel="stylesheet" href="css/owl.theme.default.min.css">
<link rel="stylesheet" href="css/magnific-popup.css">

<link rel="stylesheet" href="css/aos.css">

<link rel="stylesheet" href="css/ionicons.min.css">

<link rel="stylesheet" href="css/bootstrap-datepicker.css">
<link rel="stylesheet" href="css/jquery.timepicker.css">


<link rel="stylesheet" href="css/flaticon.css">
<link rel="stylesheet" href="css/icomoon.css">
<link rel="stylesheet" href="css/style.css">

<link rel="stylesheet"
	href="https://cdn.datatables.net/1.11.5/css/jquery.dataTables.min.css">

</head>
<body>

	<nav
		class="navbar navbar-expand-lg navbar-dark ftco_navbar bg-dark ftco-navbar-light"
		id="ftco-navbar">
		<div class="container">
			<a href="#" class="logo">Mega City<span>Cab</span></a>
			<button class="navbar-toggler" type="button" data-toggle="collapse"
				data-target="#ftco-nav" aria-controls="ftco-nav"
				aria-expanded="false" aria-label="Toggle navigation">
				<span class="oi oi-menu"></span> Menu
			</button>

			<div class="collapse navbar-collapse" id="ftco-nav">
				<ul class="navbar-nav ml-auto">
					<li class="nav-item active"><a href="index.html"
						class="nav-link">Home</a></li>
					<li class="nav-item"><a href="contact.html" class="nav-link">Book
							A Cab</a></li>
					<li class="nav-item"><a href="dashboard.jsp" class="nav-link">Dashboard</a></li>
					<li class="nav-item"><a href="login.jsp" class="nav-link">Login</a></li>
				</ul>
			</div>
		</div>
	</nav>
	<!-- END nav -->

	<div class="hero-wrap ftco-degree-bg"
		style="background-image: url('images/bg_1.jpg');"
		data-stellar-background-ratio="0.5">
		<div class="overlay"></div>
		<div class="container">
			<div
				class="row no-gutters slider-text justify-content-start align-items-center justify-content-center">
				<div class="col-lg-8 ftco-animate">
					<div class="text w-100 text-center mb-md-5 pb-md-5">
						<h1 class="mb-4">Make Your Payment</h1>
						<p style="font-size: 18px;">A small river named Duden flows by
							their place and supplies it with the necessary regelialia. It is
							a paradisematic country, in which roasted parts</p>
					</div>
				</div>
			</div>
		</div>
	</div>

	<section class="ftco-section ftco-no-pt bg-light">
		<div class="container">
			<div class="row no-gutters"></div>
	</section>

	<section class="ftco-section ftco-no-pt bg-light">
		<div class="container">
			<div class="row justify-content-center">
				<div class="col-md-12 heading-section text-center ftco-animate mb-5">
					<span class="subheading">What we offer</span>
					<h2 class="mb-2">Payment</h2>
				</div>
			</div>
			<div class="row justify-content-center">
				<div class="col-md-6">
					<div class="card shadow-lg">
						<div class="card-body">
							<h5 class="card-title text-center">Booking Details</h5>

							<%
								// Get booking details from request parameters
								String carName = request.getParameter("carName");
								String carBrand = request.getParameter("carBrand");
								double carPrice = Double.parseDouble(request.getParameter("carPrice"));
								String dateFrom = request.getParameter("dateFrom");
								String dateTo = request.getParameter("dateTo");
								int passengerCount = Integer.parseInt(request.getParameter("passengerCount"));
								String driverNeeded = request.getParameter("driverNeeded");
								String fullName = request.getParameter("fullName");
								String contactNumber = request.getParameter("contactNumber");
								String email = request.getParameter("email");

								// Calculate number of days
								SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
								Date fromDate = sdf.parse(dateFrom);
								Date toDate = sdf.parse(dateTo);
								long diffInMillies = Math.abs(toDate.getTime() - fromDate.getTime());
								long daysCount = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);

								// Calculate total amount
								double totalAmount = daysCount * carPrice;
								if (driverNeeded.equals("Yes")) {
									totalAmount += daysCount * 100; // $100 per day for driver
								}
								%>

							<p>
								<strong>Car:</strong>
								<%=carName%>
								-
								<%=carBrand%></p>
							<p>
								<strong>Price Per Day:</strong> $<%=carPrice%></p>
							<p>
								<strong>Booking Dates:</strong>
								<%=dateFrom%>
								to
								<%=dateTo%>
								(<%=daysCount%>
								days)
							</p>
							<p>
								<strong>Passenger Count:</strong>
								<%=passengerCount%></p>
							<p>
								<strong>Driver Needed:</strong>
								<%=driverNeeded%></p>
							<p>
								<strong>Total Amount:</strong> <span class="text-success">$<%=totalAmount%></span>
							</p>

							<hr>

							<h5 class="text-center">Customer Details</h5>
							<p>
								<strong>Name:</strong>
								<%=fullName%></p>
							<p>
								<strong>Contact Number:</strong>
								<%=contactNumber%></p>
							<p>
								<strong>Email:</strong>
								<%=email%></p>

							<div class="text-center">
								<a
									href="paymentProcess.jsp?totalAmount=<%=totalAmount%>&fullName=<%=fullName%>&email=<%=email%>"
									class="btn btn-primary">Proceed to Payment</a>
							</div>
						</div>
					</div>
				</div>
			</div>
	</section>


	<footer class="ftco-footer ftco-bg-dark ftco-section">
		<div class="container">
			<div class="row mb-5">
				<div class="col-md">
					<div class="ftco-footer-widget mb-4">
						<h2 class="ftco-heading-2">
							<a href="#" class="logo">Mega City<span>Cab</span></a>
						</h2>
						<p>Far far away, behind the word mountains, far from the
							countries Vokalia and Consonantia, there live the blind texts.</p>
					</div>
				</div>
			</div>
		</div>
	</footer>



	<!-- loader -->
	<div id="ftco-loader" class="show fullscreen">
		<svg class="circular" width="48px" height="48px">
			<circle class="path-bg" cx="24" cy="24" r="22" fill="none"
				stroke-width="4" stroke="#eeeeee" />
			<circle class="path" cx="24" cy="24" r="22" fill="none"
				stroke-width="4" stroke-miterlimit="10" stroke="#F96D00" /></svg>
	</div>


	<script src="js/jquery.min.js"></script>
	<script src="js/jquery-migrate-3.0.1.min.js"></script>
	<script src="js/popper.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/jquery.easing.1.3.js"></script>
	<script src="js/jquery.waypoints.min.js"></script>
	<script src="js/jquery.stellar.min.js"></script>
	<script src="js/owl.carousel.min.js"></script>
	<script src="js/jquery.magnific-popup.min.js"></script>
	<script src="js/aos.js"></script>
	<script src="js/jquery.animateNumber.min.js"></script>
	<script src="js/bootstrap-datepicker.js"></script>
	<script src="js/jquery.timepicker.min.js"></script>
	<script src="js/scrollax.min.js"></script>
	<script
		src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBVWaKrjvy3MaE7SQ74_uJiULgl1JY0H2s&sensor=false"></script>
	<script src="js/google-map.js"></script>
	<script src="js/main.js"></script>
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	<script
		src="https://cdn.datatables.net/1.11.5/js/jquery.dataTables.min.js"></script>




</body>
</html>