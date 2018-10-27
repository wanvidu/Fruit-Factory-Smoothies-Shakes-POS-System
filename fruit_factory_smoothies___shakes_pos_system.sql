-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 29, 2018 at 02:41 PM
-- Server version: 10.1.29-MariaDB
-- PHP Version: 7.2.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `fruit_factory_smoothies_&_shakes_pos_system`
--

-- --------------------------------------------------------

--
-- Table structure for table `customers123456789`
--

CREATE TABLE `customers123456789` (
  `CustomerID` int(11) NOT NULL,
  `Name` varchar(20) DEFAULT NULL,
  `Address` varchar(100) DEFAULT NULL,
  `PhoneNumber` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `customers123456789`
--

INSERT INTO `customers123456789` (`CustomerID`, `Name`, `Address`, `PhoneNumber`) VALUES
(50000, 'Sarath', 'First Road, Nawala', '0774258674');

-- --------------------------------------------------------

--
-- Table structure for table `emp123456789`
--

CREATE TABLE `emp123456789` (
  `EmpID` int(11) NOT NULL,
  `NIC` varchar(20) DEFAULT NULL,
  `EmpName` varchar(20) DEFAULT NULL,
  `Age` int(11) DEFAULT NULL,
  `Password` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `emp123456789`
--

INSERT INTO `emp123456789` (`EmpID`, `NIC`, `EmpName`, `Age`, `Password`) VALUES
(100, '954783145v', 'Nethu', 23, 'nethu123');

-- --------------------------------------------------------

--
-- Table structure for table `ingredients123456789`
--

CREATE TABLE `ingredients123456789` (
  `IngredientID` int(11) NOT NULL,
  `Name` varchar(20) DEFAULT NULL,
  `UnitPrice` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `ingredients123456789`
--

INSERT INTO `ingredients123456789` (`IngredientID`, `Name`, `UnitPrice`) VALUES
(1000, 'Lemon', 100),
(1002, 'Suger', 50),
(1003, 'Water', 20),
(1004, 'Ginger', 30),
(1005, 'Watermelon', 200),
(1006, 'Cucumber', 150),
(1007, 'Mango', 50),
(1008, 'Pineapple', 250),
(1009, 'Mulberries', 35),
(1011, 'Milk', 240);

-- --------------------------------------------------------

--
-- Table structure for table `item123456789`
--

CREATE TABLE `item123456789` (
  `RecipeID` int(11) NOT NULL,
  `IngredientID` int(11) NOT NULL,
  `Quantity` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `item123456789`
--

INSERT INTO `item123456789` (`RecipeID`, `IngredientID`, `Quantity`) VALUES
(1, 1000, 3),
(1, 1002, 5),
(3, 1002, 3);

-- --------------------------------------------------------

--
-- Table structure for table `order123456789`
--

CREATE TABLE `order123456789` (
  `OrderID` int(11) NOT NULL,
  `ItemID` int(11) NOT NULL,
  `ItemQuantity` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `order123456789`
--

INSERT INTO `order123456789` (`OrderID`, `ItemID`, `ItemQuantity`) VALUES
(10050, 1, 3),
(10050, 3, 5),
(10100, 3, 2);

-- --------------------------------------------------------

--
-- Table structure for table `recipenames123456789`
--

CREATE TABLE `recipenames123456789` (
  `RecipeID` int(11) NOT NULL,
  `Name` varchar(100) DEFAULT NULL,
  `UnitPrice` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `recipenames123456789`
--

INSERT INTO `recipenames123456789` (`RecipeID`, `Name`, `UnitPrice`) VALUES
(1, 'Watermelon and Mint Juice', 200),
(3, 'Cool Kiwi Juice', 100),
(5, 'Aam Ras', 150),
(7, 'Ananas da Panna', 130),
(9, 'Lychee and Dill Juice', 90),
(11, 'Very Berry Khatta', 80),
(13, 'Virginia Punch', 65),
(15, 'Grape Nectar', 250),
(17, 'Grape Nectar', 70),
(19, 'Plum-ness', 200),
(21, 'Orange and Basil Juice', 50);

-- --------------------------------------------------------

--
-- Table structure for table `supplierdetails123456789`
--

CREATE TABLE `supplierdetails123456789` (
  `SupplierID` int(11) NOT NULL,
  `SupplierName` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `supplierdetails123456789`
--

INSERT INTO `supplierdetails123456789` (`SupplierID`, `SupplierName`) VALUES
(700, 'Fresh Foods'),
(750, 'Takas');

-- --------------------------------------------------------

--
-- Table structure for table `suppliersupply123456789`
--

CREATE TABLE `suppliersupply123456789` (
  `SupplierID` int(11) NOT NULL,
  `IngredientID` int(11) NOT NULL,
  `Quantity` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `suppliersupply123456789`
--

INSERT INTO `suppliersupply123456789` (`SupplierID`, `IngredientID`, `Quantity`) VALUES
(700, 1000, 3),
(700, 1002, 5),
(750, 1002, 3);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `customers123456789`
--
ALTER TABLE `customers123456789`
  ADD PRIMARY KEY (`CustomerID`);

--
-- Indexes for table `emp123456789`
--
ALTER TABLE `emp123456789`
  ADD PRIMARY KEY (`EmpID`);

--
-- Indexes for table `ingredients123456789`
--
ALTER TABLE `ingredients123456789`
  ADD PRIMARY KEY (`IngredientID`);

--
-- Indexes for table `item123456789`
--
ALTER TABLE `item123456789`
  ADD PRIMARY KEY (`RecipeID`,`IngredientID`),
  ADD KEY `C6_123456789` (`IngredientID`);

--
-- Indexes for table `order123456789`
--
ALTER TABLE `order123456789`
  ADD PRIMARY KEY (`OrderID`,`ItemID`),
  ADD KEY `C8_123456789` (`ItemID`);

--
-- Indexes for table `recipenames123456789`
--
ALTER TABLE `recipenames123456789`
  ADD PRIMARY KEY (`RecipeID`);

--
-- Indexes for table `supplierdetails123456789`
--
ALTER TABLE `supplierdetails123456789`
  ADD PRIMARY KEY (`SupplierID`);

--
-- Indexes for table `suppliersupply123456789`
--
ALTER TABLE `suppliersupply123456789`
  ADD PRIMARY KEY (`SupplierID`,`IngredientID`),
  ADD KEY `C13_123456789` (`IngredientID`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `item123456789`
--
ALTER TABLE `item123456789`
  ADD CONSTRAINT `C5_123456789` FOREIGN KEY (`RecipeID`) REFERENCES `recipenames123456789` (`RecipeID`) ON DELETE CASCADE,
  ADD CONSTRAINT `C6_123456789` FOREIGN KEY (`IngredientID`) REFERENCES `ingredients123456789` (`IngredientID`);

--
-- Constraints for table `order123456789`
--
ALTER TABLE `order123456789`
  ADD CONSTRAINT `C8_123456789` FOREIGN KEY (`ItemID`) REFERENCES `item123456789` (`RecipeID`);

--
-- Constraints for table `suppliersupply123456789`
--
ALTER TABLE `suppliersupply123456789`
  ADD CONSTRAINT `C12_123456789` FOREIGN KEY (`SupplierID`) REFERENCES `supplierdetails123456789` (`SupplierID`) ON DELETE CASCADE,
  ADD CONSTRAINT `C13_123456789` FOREIGN KEY (`IngredientID`) REFERENCES `ingredients123456789` (`IngredientID`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
