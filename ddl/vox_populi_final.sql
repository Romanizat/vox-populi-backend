-- phpMyAdmin SQL Dump
-- version 5.1.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jan 29, 2022 at 07:33 PM
-- Server version: 10.4.18-MariaDB
-- PHP Version: 7.3.27

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `vox_populi`
--
CREATE DATABASE IF NOT EXISTS `vox_populi` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `vox_populi`;

-- --------------------------------------------------------

--
-- Table structure for table `event`
--

DROP TABLE IF EXISTS `event`;
CREATE TABLE IF NOT EXISTS `event` (
  `id_event` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(1024) NOT NULL,
  `date` datetime DEFAULT NULL,
  `location` varchar(1024) DEFAULT NULL,
  `banned` tinyint(1) NOT NULL DEFAULT 0,
  `created_date` timestamp NOT NULL DEFAULT current_timestamp(),
  `last_modified_date` timestamp NOT NULL DEFAULT current_timestamp(),
  `last_modified_by` varchar(64) DEFAULT 'system',
  `record_status` int(11) DEFAULT 1,
  PRIMARY KEY (`id_event`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;

--
-- RELATIONSHIPS FOR TABLE `event`:
--

--
-- Dumping data for table `event`
--

INSERT INTO `event` (`id_event`, `name`, `date`, `location`, `banned`, `created_date`, `last_modified_date`, `last_modified_by`, `record_status`) VALUES
(1, 'New Year\'s Eve', '2021-12-30 23:00:00', 'Time Square, NY', 0, '2021-12-27 16:53:05', '2021-12-27 16:53:05', 'anonymousUser', 1);

-- --------------------------------------------------------

--
-- Table structure for table `event_participant`
--

DROP TABLE IF EXISTS `event_participant`;
CREATE TABLE IF NOT EXISTS `event_participant` (
  `id_event_participant` int(11) NOT NULL AUTO_INCREMENT,
  `id_user` int(11) NOT NULL,
  `id_event` int(11) NOT NULL,
  `organizer` tinyint(1) NOT NULL,
  `created_date` timestamp NOT NULL DEFAULT current_timestamp(),
  `last_modified_date` timestamp NOT NULL DEFAULT current_timestamp(),
  `last_modified_by` varchar(64) DEFAULT 'system',
  `record_status` int(11) DEFAULT 1,
  PRIMARY KEY (`id_event_participant`),
  KEY `fk_contains` (`id_event`),
  KEY `fk_is` (`id_user`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4;

--
-- RELATIONSHIPS FOR TABLE `event_participant`:
--   `id_event`
--       `event` -> `id_event`
--   `id_user`
--       `user` -> `id_user`
--

--
-- Dumping data for table `event_participant`
--

INSERT INTO `event_participant` (`id_event_participant`, `id_user`, `id_event`, `organizer`, `created_date`, `last_modified_date`, `last_modified_by`, `record_status`) VALUES
(1, 1, 1, 1, '2021-12-27 16:53:05', '2021-12-27 16:53:05', 'anonymousUser', 1),
(7, 3, 1, 0, '2022-01-29 12:00:25', '2022-01-29 12:00:25', 'anonymousUser', 1);

-- --------------------------------------------------------

--
-- Table structure for table `event_suggestion`
--

DROP TABLE IF EXISTS `event_suggestion`;
CREATE TABLE IF NOT EXISTS `event_suggestion` (
  `id_event_suggestion` int(11) NOT NULL AUTO_INCREMENT,
  `id_event` int(11) NOT NULL,
  `title` varchar(1024) NOT NULL,
  `url` varchar(1024) NOT NULL,
  `position` int(11) NOT NULL,
  `created_date` timestamp NOT NULL DEFAULT current_timestamp(),
  `last_modified_date` timestamp NOT NULL DEFAULT current_timestamp(),
  `last_modified_by` varchar(64) DEFAULT 'system',
  `record_status` int(11) DEFAULT 1,
  PRIMARY KEY (`id_event_suggestion`),
  KEY `fk_shows` (`id_event`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4;

--
-- RELATIONSHIPS FOR TABLE `event_suggestion`:
--   `id_event`
--       `event` -> `id_event`
--

--
-- Dumping data for table `event_suggestion`
--

INSERT INTO `event_suggestion` (`id_event_suggestion`, `id_event`, `title`, `url`, `position`, `created_date`, `last_modified_date`, `last_modified_by`, `record_status`) VALUES
(1, 1, 'J Balvin - Mi Gente', 'wnJ6LuUFpMo', 2, '2022-01-25 17:47:51', '2022-01-29 14:05:53', 'anonymousUser', 1),
(2, 1, 'Nina Simone, Sofi Tukker - Sinnerman (Sofi Tukker Remix)', 'MubdXpjydqs', 1, '2022-01-25 18:51:58', '2022-01-29 14:05:53', 'anonymousUser', 1),
(5, 1, 'J BALVIN x BAD BUNNY - LA CANCIÓN | OASIS', 'LxOTsiV4tkQ', 0, '2022-01-29 10:51:45', '2022-01-29 14:05:53', 'anonymousUser', 1),
(6, 1, 'BAD BUNNY - NI BIEN NI MAL | X100PRE', '4oiZdbwxUuQ', 3, '2022-01-29 10:53:25', '2022-01-29 14:05:53', 'anonymousUser', 1);

-- --------------------------------------------------------

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
CREATE TABLE IF NOT EXISTS `role` (
  `id_role` int(11) NOT NULL AUTO_INCREMENT,
  `role` varchar(1024) NOT NULL,
  `created_date` timestamp NOT NULL DEFAULT current_timestamp(),
  `last_modified_date` timestamp NOT NULL DEFAULT current_timestamp(),
  `last_modified_by` varchar(64) DEFAULT 'system',
  `record_status` int(11) DEFAULT 1,
  PRIMARY KEY (`id_role`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;

--
-- RELATIONSHIPS FOR TABLE `role`:
--

--
-- Dumping data for table `role`
--

INSERT INTO `role` (`id_role`, `role`, `created_date`, `last_modified_date`, `last_modified_by`, `record_status`) VALUES
(1, 'USER', '2021-11-29 20:08:41', '2021-11-29 20:08:41', 'system', 1),
(2, 'ADMIN', '2021-11-29 20:08:49', '2021-11-29 20:08:49', 'system', 1);

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user` (
  `id_user` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(1024) NOT NULL,
  `password` varchar(1024) NOT NULL,
  `email` varchar(1024) NOT NULL,
  `name` varchar(1024) NOT NULL,
  `last_name` varchar(1024) NOT NULL,
  `banned` tinyint(1) NOT NULL DEFAULT 0,
  `created_date` timestamp NOT NULL DEFAULT current_timestamp(),
  `last_modified_date` timestamp NOT NULL DEFAULT current_timestamp(),
  `last_modified_by` varchar(64) DEFAULT 'system',
  `record_status` int(11) DEFAULT 1,
  PRIMARY KEY (`id_user`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4;

--
-- RELATIONSHIPS FOR TABLE `user`:
--

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id_user`, `username`, `password`, `email`, `name`, `last_name`, `banned`, `created_date`, `last_modified_date`, `last_modified_by`, `record_status`) VALUES
(1, 'admin', '$2a$12$kGv6rd0Suxowz6JDhDK/N.ZmCTMZ7dCfQjRT8om/kIfAsG9bXlbCO', 'admin@romanizat.com', 'Ad', 'Min', 0, '2021-11-29 20:08:11', '2021-12-22 23:20:41', 'anonymousUser', 1),
(3, 'romanizat', '$2a$10$wv76Hwia3Rt.1F5y6RylNuiyBUZo56ARp90Qol58XEVyYjXQ2Se2y', 'marko.josifovic.4494@metropolitan.ac.rs', 'Marko', 'Josifović', 0, '2021-12-22 23:08:10', '2021-12-23 00:18:22', 'anonymousUser', 1),
(5, 'test', '$2a$10$Agjlhl00Bl6buCZMdS8nces1EPmpJF/qa.kTfM71JxdUWuhXKmf6e', 'test', 'Test', 'Test', 0, '2021-12-22 23:10:56', '2021-12-22 23:10:56', 'anonymousUser', 1),
(6, 'testovski', '$2a$10$YVyWxELBK4p1deCrO9H04uJWc4NzDunzFSzZ6j3u7mR97jaNGupsC', 'testovski', 'testić', 'Testić', 0, '2021-12-23 21:46:30', '2021-12-23 21:46:30', 'anonymousUser', 1);

-- --------------------------------------------------------

--
-- Table structure for table `user_role`
--

DROP TABLE IF EXISTS `user_role`;
CREATE TABLE IF NOT EXISTS `user_role` (
  `id_role` int(11) NOT NULL,
  `id_user` int(11) NOT NULL,
  `created_date` timestamp NOT NULL DEFAULT current_timestamp(),
  `last_modified_date` timestamp NOT NULL DEFAULT current_timestamp(),
  `last_modified_by` varchar(64) DEFAULT 'system',
  `record_status` int(11) DEFAULT 1,
  PRIMARY KEY (`id_role`,`id_user`),
  KEY `fk_has2` (`id_user`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- RELATIONSHIPS FOR TABLE `user_role`:
--   `id_role`
--       `role` -> `id_role`
--   `id_user`
--       `user` -> `id_user`
--

--
-- Dumping data for table `user_role`
--

INSERT INTO `user_role` (`id_role`, `id_user`, `created_date`, `last_modified_date`, `last_modified_by`, `record_status`) VALUES
(1, 1, '2021-11-29 20:09:07', '2021-11-29 20:09:07', 'system', 1),
(1, 3, '2021-12-22 23:08:11', '2021-12-22 23:08:11', 'system', 1),
(1, 5, '2021-12-22 23:10:57', '2021-12-22 23:10:57', 'system', 1),
(1, 6, '2021-12-23 21:46:30', '2021-12-23 21:46:30', 'system', 1),
(2, 1, '2021-11-29 20:09:07', '2021-11-29 20:09:07', 'system', 1);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `event_participant`
--
ALTER TABLE `event_participant`
  ADD CONSTRAINT `fk_contains` FOREIGN KEY (`id_event`) REFERENCES `event` (`id_event`),
  ADD CONSTRAINT `fk_is` FOREIGN KEY (`id_user`) REFERENCES `user` (`id_user`);

--
-- Constraints for table `event_suggestion`
--
ALTER TABLE `event_suggestion`
  ADD CONSTRAINT `fk_shows` FOREIGN KEY (`id_event`) REFERENCES `event` (`id_event`);

--
-- Constraints for table `user_role`
--
ALTER TABLE `user_role`
  ADD CONSTRAINT `fk_has` FOREIGN KEY (`id_role`) REFERENCES `role` (`id_role`),
  ADD CONSTRAINT `fk_has2` FOREIGN KEY (`id_user`) REFERENCES `user` (`id_user`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
