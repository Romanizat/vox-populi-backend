-- phpMyAdmin SQL Dump
-- version 5.1.3
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jan 17, 2023 at 10:37 PM
-- Server version: 10.4.22-MariaDB
-- PHP Version: 7.4.28

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
(1, 'New Year\'s Eve', '2021-12-30 23:00:00', 'Time Square, NY', 0, '2021-12-27 15:53:05', '2021-12-27 15:53:05', 'anonymousUser', 1);

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
(1, 1, 1, 1, '2021-12-27 15:53:05', '2021-12-27 15:53:05', 'anonymousUser', 1),
(7, 3, 1, 0, '2022-01-29 11:00:25', '2022-01-29 11:00:25', 'anonymousUser', 1);

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
  `creator_user_fk` int(11) NOT NULL,
  `created_date` timestamp NOT NULL DEFAULT current_timestamp(),
  `last_modified_date` timestamp NOT NULL DEFAULT current_timestamp(),
  `last_modified_by` varchar(64) DEFAULT 'system',
  `record_status` int(11) DEFAULT 1,
  PRIMARY KEY (`id_event_suggestion`),
  KEY `fk_shows` (`id_event`),
  KEY `creator_user_fk` (`creator_user_fk`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4;

--
-- RELATIONSHIPS FOR TABLE `event_suggestion`:
--   `creator_user_fk`
--       `user` -> `id_user`
--   `id_event`
--       `event` -> `id_event`
--

--
-- Dumping data for table `event_suggestion`
--

INSERT INTO `event_suggestion` (`id_event_suggestion`, `id_event`, `title`, `url`, `position`, `creator_user_fk`, `created_date`, `last_modified_date`, `last_modified_by`, `record_status`) VALUES
(1, 1, 'J Balvin - Mi Gente', 'wnJ6LuUFpMo', 3, 1, '2022-01-25 16:47:51', '2023-01-12 21:41:45', 'anonymousUser', 1),
(2, 1, 'Nina Simone, Sofi Tukker - Sinnerman (Sofi Tukker Remix)', 'MubdXpjydqs', 0, 1, '2022-01-25 17:51:58', '2023-01-16 22:20:54', 'anonymousUser', 1),
(5, 1, 'J BALVIN x BAD BUNNY - LA CANCIÓN | OASIS', 'LxOTsiV4tkQ', 1, 1, '2022-01-29 09:51:45', '2023-01-16 22:20:54', 'anonymousUser', 1),
(6, 1, 'BAD BUNNY - NI BIEN NI MAL | X100PRE', '4oiZdbwxUuQ', 2, 1, '2022-01-29 09:53:25', '2023-01-12 21:41:44', 'anonymousUser', 1),
(8, 1, 'Simone Cisternino - Knight Rider', 'yyEJI4c-24A', 4, 3, '2023-01-17 00:26:34', '2023-01-17 00:26:34', 'anonymousUser', 1),
(9, 1, 'Jengi - Bel Mercy', 'Q8hkQcfAJzU', 5, 3, '2023-01-17 00:33:36', '2023-01-17 00:33:36', 'anonymousUser', 1),
(15, 1, 'SOFI TUKKER x Gorgon City - House Arrest (Chris Lorenzo Remix)', 'kN2YsIU7SAA', 6, 3, '2023-01-17 00:57:12', '2023-01-17 00:57:12', 'anonymousUser', 1);

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
(1, 'USER', '2021-11-29 19:08:41', '2021-11-29 19:08:41', 'system', 1),
(2, 'ADMIN', '2021-11-29 19:08:49', '2021-11-29 19:08:49', 'system', 1);

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
  `profile_photo` longtext NOT NULL,
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

INSERT INTO `user` (`id_user`, `username`, `password`, `email`, `name`, `last_name`, `profile_photo`, `banned`, `created_date`, `last_modified_date`, `last_modified_by`, `record_status`) VALUES
(1, 'admin', '$2a$12$kGv6rd0Suxowz6JDhDK/N.ZmCTMZ7dCfQjRT8om/kIfAsG9bXlbCO', 'admin@romanizat.com', 'Ad', 'Min', '', 0, '2021-11-29 19:08:11', '2021-12-22 22:20:41', 'anonymousUser', 1),
(3, 'romanizat', '$2a$10$wv76Hwia3Rt.1F5y6RylNuiyBUZo56ARp90Qol58XEVyYjXQ2Se2y', 'marko.josifovic.4494@metropolitan.ac.rs', 'Marko', 'Josifović', '', 0, '2021-12-22 22:08:10', '2021-12-22 23:18:22', 'anonymousUser', 1),
(5, 'test', '$2a$10$Agjlhl00Bl6buCZMdS8nces1EPmpJF/qa.kTfM71JxdUWuhXKmf6e', 'test', 'Test', 'Test', '', 0, '2021-12-22 22:10:56', '2022-12-19 22:17:43', 'anonymousUser', 1),
(6, 'testovski', '$2a$10$YVyWxELBK4p1deCrO9H04uJWc4NzDunzFSzZ6j3u7mR97jaNGupsC', 'testovski', 'testić', 'Testić', '', 0, '2021-12-23 20:46:30', '2023-01-16 22:20:05', 'anonymousUser', 1);

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
(1, 1, '2021-11-29 19:09:07', '2021-11-29 19:09:07', 'system', 1),
(1, 3, '2021-12-22 22:08:11', '2021-12-22 22:08:11', 'system', 1),
(1, 5, '2021-12-22 22:10:57', '2021-12-22 22:10:57', 'system', 1),
(1, 6, '2021-12-23 20:46:30', '2021-12-23 20:46:30', 'system', 1),
(2, 1, '2021-11-29 19:09:07', '2021-11-29 19:09:07', 'system', 1);

-- --------------------------------------------------------

--
-- Table structure for table `vote`
--

DROP TABLE IF EXISTS `vote`;
CREATE TABLE IF NOT EXISTS `vote` (
  `id_vote` int(11) NOT NULL AUTO_INCREMENT,
  `id_event_suggestion` int(11) NOT NULL,
  `id_user` int(11) NOT NULL,
  `upvote` tinyint(1) NOT NULL,
  PRIMARY KEY (`id_vote`),
  UNIQUE KEY `id_event_suggestion_2` (`id_event_suggestion`,`id_user`),
  KEY `id_event_suggestion` (`id_event_suggestion`),
  KEY `id_user` (`id_user`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4;

--
-- RELATIONSHIPS FOR TABLE `vote`:
--   `id_event_suggestion`
--       `event_suggestion` -> `id_event_suggestion`
--   `id_user`
--       `user` -> `id_user`
--

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
  ADD CONSTRAINT `event_suggestion_ibfk_1` FOREIGN KEY (`creator_user_fk`) REFERENCES `user` (`id_user`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_shows` FOREIGN KEY (`id_event`) REFERENCES `event` (`id_event`);

--
-- Constraints for table `user_role`
--
ALTER TABLE `user_role`
  ADD CONSTRAINT `fk_has` FOREIGN KEY (`id_role`) REFERENCES `role` (`id_role`),
  ADD CONSTRAINT `fk_has2` FOREIGN KEY (`id_user`) REFERENCES `user` (`id_user`);

--
-- Constraints for table `vote`
--
ALTER TABLE `vote`
  ADD CONSTRAINT `vote_ibfk_1` FOREIGN KEY (`id_event_suggestion`) REFERENCES `event_suggestion` (`id_event_suggestion`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `vote_ibfk_2` FOREIGN KEY (`id_user`) REFERENCES `user` (`id_user`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
