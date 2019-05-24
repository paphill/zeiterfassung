-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Erstellungszeit: 24. Mai 2019 um 21:15
-- Server-Version: 10.1.35-MariaDB
-- PHP-Version: 7.2.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Datenbank: `zeiterfassung`
--

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `mitarbeiter`
--

CREATE TABLE `mitarbeiter` (
  `id` int(11) NOT NULL,
  `vorname` varchar(45) DEFAULT NULL,
  `nachname` varchar(45) DEFAULT NULL,
  `typ` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Daten für Tabelle `mitarbeiter`
--

INSERT INTO `mitarbeiter` (`id`, `vorname`, `nachname`, `typ`) VALUES
(1, 'Lukas', 'Posch', 'Buchhaltung'),
(2, 'Philipp', 'Pali', 'Admin'),
(3, 'Fabio', 'Parth', 'Einkauf/Verkauf'),
(4, 'Harry', 'Triendl', 'Lagerhaltung');

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `projekt`
--

CREATE TABLE `projekt` (
  `id` int(11) NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  `auftraggeber` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Daten für Tabelle `projekt`
--

INSERT INTO `projekt` (`id`, `name`, `auftraggeber`) VALUES
(1, 'Zeiterfassung', 'Neuner'),
(2, 'Chatprogramm', 'Marberger'),
(3, 'Rechnung-GUI', 'Sparkasse'),
(4, 'Anmeldung', 'Karrösten'),
(5, 'Kunstapp', 'Kunstforum S.');

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `taetigkeit`
--

CREATE TABLE `taetigkeit` (
  `id` int(11) NOT NULL,
  `mitarid` varchar(45) DEFAULT NULL,
  `projid` varchar(45) DEFAULT NULL,
  `arbeitszeit` varchar(45) DEFAULT NULL,
  `beschreibung` varchar(45) DEFAULT NULL,
  `datum` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Daten für Tabelle `taetigkeit`
--

INSERT INTO `taetigkeit` (`id`, `mitarid`, `projid`, `arbeitszeit`, `beschreibung`, `datum`) VALUES
(1, '2 - Philipp Pali', '1 - Zeiterfassung', '3', 'Mehrere Tabs, Dritte Tabelle', '2019-05-21'),
(2, '1 - Lukas Posch', '1 - Zeiterfassung', '2', 'Export, Datenbank', '2019-05-20'),
(3, '2 - Philipp Pali', '1 - Zeiterfassung', '1', 'Datum Spalte', '2019-05-21'),
(4, '1 - Lukas Posch', '2 - Chatprogramm', '2', 'Datenbank überarbeiten', '2019-05-22'),
(5, '3 - Fabio Parth', '2 - Chatprogramm', '1', 'Überarbeitung Server', '2019-05-17'),
(6, '3 - Fabio Parth', '2 - Chatprogramm', '3', 'Client/Server einrichten', '2019-05-07'),
(7, '1 - Lukas Posch', '2 - Chatprogramm', '5', 'Verbindung aufbauen', '2019-05-06'),
(8, '3 - Fabio Parth', '1 - Zeiterfassung', '1', 'Mitarbeiter SQL', '2019-05-20'),
(9, '2 - Philipp Pali', '4 - Anmeldung', '3', 'GUI-Controller', '2019-03-23'),
(10, '2 - Philipp Pali', '4 - Anmeldung', '2', 'Beziehung zwischen Tabellen', '2019-03-15'),
(11, '1 - Lukas Posch', '3 - Rechnung-GUI', '5', 'Komplettes Programm fertiggestellt', '2019-03-31'),
(12, '3 - Fabio Parth', '3 - Rechnung-GUI', '1', 'Teambesprechung', '2019-03-31');

--
-- Indizes der exportierten Tabellen
--

--
-- Indizes für die Tabelle `mitarbeiter`
--
ALTER TABLE `mitarbeiter`
  ADD PRIMARY KEY (`id`);

--
-- Indizes für die Tabelle `projekt`
--
ALTER TABLE `projekt`
  ADD PRIMARY KEY (`id`);

--
-- Indizes für die Tabelle `taetigkeit`
--
ALTER TABLE `taetigkeit`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT für exportierte Tabellen
--

--
-- AUTO_INCREMENT für Tabelle `mitarbeiter`
--
ALTER TABLE `mitarbeiter`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT für Tabelle `projekt`
--
ALTER TABLE `projekt`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT für Tabelle `taetigkeit`
--
ALTER TABLE `taetigkeit`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
