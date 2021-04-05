-- phpMyAdmin SQL Dump
-- version 5.0.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Dec 10, 2020 at 11:27 PM
-- Server version: 10.4.16-MariaDB
-- PHP Version: 7.4.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `login`
--

-- --------------------------------------------------------

--
-- Table structure for table `cards`
--

CREATE TABLE `cards` (
  `id` int(3) NOT NULL,
  `cardNum` int(16) NOT NULL,
  `expiry` varchar(5) NOT NULL,
  `cvv` int(3) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `securityanswers`
--

CREATE TABLE `securityanswers` (
  `id` int(3) NOT NULL,
  `userId` int(3) NOT NULL,
  `question` int(3) NOT NULL,
  `answer` varchar(512) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `securityquestions`
--

CREATE TABLE `securityquestions` (
  `id` int(3) NOT NULL,
  `question` varchar(130) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `securityquestions`
--

INSERT INTO `securityquestions` (`id`, `question`) VALUES
(1, 'What was the name of your childhood pet?'),
(2, 'Where did you go to primary school?'),
(3, 'What was the house number and street name you lived in as a child?'),
(4, 'What were the last four digits of your childhood telephone number?'),
(5, 'In what town or city was your first full time job?'),
(6, 'What is your grandmother\'s (on your mother\'s side) maiden name?'),
(7, 'What is your spouse or partner\'s mother\'s maiden name?'),
(8, 'When did you get your first car?');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` int(3) NOT NULL,
  `username` varchar(20) NOT NULL,
  `password` varchar(512) NOT NULL,
  `email` varchar(50) NOT NULL,
  `paymentDetails` int(3) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `username`, `password`, `email`, `paymentDetails`) VALUES
(1, 'osama', 'osama', 'osama@someemail.com', NULL),
(3, 'leigh', 'lll', 'LB@G.C', NULL),
(4, 'Briananj_1', '100000:a375ba50732e74dba7e2485a895535ac:c640fdfc9328a7fe624ee0cbac5cd4f95ab0dd7234a48b180b2d65c142765adc81125b9c36500cd0b73b2a8808c08c04d90e736c83ee69236232acf63c159e51', 'bJohnson@123.ie', NULL);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `cards`
--
ALTER TABLE `cards`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `securityanswers`
--
ALTER TABLE `securityanswers`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_userId` (`userId`),
  ADD KEY `fk_securityQuestion` (`question`);

--
-- Indexes for table `securityquestions`
--
ALTER TABLE `securityquestions`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_paymentDetails` (`paymentDetails`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `cards`
--
ALTER TABLE `cards`
  MODIFY `id` int(3) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `securityanswers`
--
ALTER TABLE `securityanswers`
  MODIFY `id` int(3) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `securityquestions`
--
ALTER TABLE `securityquestions`
  MODIFY `id` int(3) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(3) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `securityanswers`
--
ALTER TABLE `securityanswers`
  ADD CONSTRAINT `fk_securityQuestion` FOREIGN KEY (`question`) REFERENCES `securityquestions` (`id`),
  ADD CONSTRAINT `fk_userId` FOREIGN KEY (`userId`) REFERENCES `users` (`id`);

--
-- Constraints for table `users`
--
ALTER TABLE `users`
  ADD CONSTRAINT `fk_paymentDetails` FOREIGN KEY (`paymentDetails`) REFERENCES `cards` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
