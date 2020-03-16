-- phpMyAdmin SQL Dump
-- version 4.6.4
-- https://www.phpmyadmin.net/
--
-- Client :  127.0.0.1
-- Généré le :  Sam 14 Mars 2020 à 18:07
-- Version du serveur :  5.7.14
-- Version de PHP :  5.6.25

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `webShooting`
--

-- --------------------------------------------------------

--
-- Structure de la table `ws_album`
--

CREATE TABLE `ws_album` (
  `id` int(11) NOT NULL,
  `nom` varchar(50) NOT NULL,
  `type` enum('PUBLIC','PRIVATE') NOT NULL,
  `theme` enum('Vacances','Sport','Loisirs','Sortie','Autres') NOT NULL,
  `login_user` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `ws_album`
--

INSERT INTO `ws_album` (`id`, `nom`, `type`, `theme`, `login_user`) VALUES
(1, 'M2GLSI', 'PUBLIC', 'Sortie', 'maguie'),
(2, 'Football', 'PUBLIC', 'Sport', 'abdou'),
(6, 'Shooting', 'PUBLIC', 'Vacances', 'maguie'),
(12, 'Entreprise', 'PUBLIC', 'Autres', 'abdou');

-- --------------------------------------------------------

--
-- Structure de la table `ws_images`
--

CREATE TABLE `ws_images` (
  `id` int(11) NOT NULL,
  `titre` varchar(50) NOT NULL,
  `description` text NOT NULL,
  `hauteur` int(11) NOT NULL,
  `largeur` int(11) NOT NULL,
  `url` varchar(100) NOT NULL,
  `date_creation` datetime NOT NULL,
  `date_update` datetime NOT NULL,
  `album` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `ws_images`
--

INSERT INTO `ws_images` (`id`, `titre`, `description`, `hauteur`, `largeur`, `url`, `date_creation`, `date_update`, `album`) VALUES
(1, 'petite pause cafe', 'promenade', 612, 408, 'abdou4.jpg', '2020-03-14 18:00:25', '2020-03-14 18:00:25', 6),
(2, 'Cours de l\'esp', 'mode jean', 3264, 2448, 'esp2.jpg', '2020-03-14 18:03:21', '2020-03-14 18:03:21', 1),
(3, 'M2GLSI vs TELECOM', 'match de fin de promo score 6 - 6\r\n', 3264, 2448, 'foot1.jpg', '2020-03-14 18:03:39', '2020-03-14 18:03:39', 2),
(4, 'Instant pause', 'Terrain basket de l\'esp', 1080, 810, 'esp10.jpg', '2020-03-14 18:04:01', '2020-03-14 18:04:01', 1),
(6, 'fun', 'intant shooting', 608, 1003, 'esp8.jpg', '2020-03-14 18:04:19', '2020-03-14 18:04:19', 6),
(7, 'Gana', 'coupe du monde 2018', 685, 900, 'foot2.jpg', '2020-03-14 18:04:39', '2020-03-14 18:04:39', 2),
(8, 'but de coutinho', 'league des champions', 159, 316, 'foot3.jpg', '2020-03-14 18:04:56', '2020-03-14 18:04:56', 2),
(9, 'stage', 'A l\'agence nationale de la statistique et de la demographie', 853, 1280, 'ansd.jpg', '2020-03-14 18:05:15', '2020-03-14 18:05:15', 12),
(10, 'coaching', 'DSC Dakar Sacre coeur', 935, 750, 'abdou5.jpg', '2020-03-14 18:05:34', '2020-03-14 18:05:34', 2),
(11, 'School', 'Master GLSI', 945, 709, 'esp6.jpg', '2020-03-14 18:05:56', '2020-03-14 18:05:56', 1);

-- --------------------------------------------------------

--
-- Structure de la table `ws_utilisateurs`
--

CREATE TABLE `ws_utilisateurs` (
  `id` int(11) NOT NULL,
  `prenom` varchar(100) NOT NULL,
  `nom` varchar(50) NOT NULL,
  `login` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `ws_utilisateurs`
--

INSERT INTO `ws_utilisateurs` (`id`, `prenom`, `nom`, `login`, `password`) VALUES
(1, 'Maguette', 'Ndiaye', 'maguie', 'passer'),
(2, 'Abdou', 'Kebe', 'abdou', 'admin');

--
-- Index pour les tables exportées
--

--
-- Index pour la table `ws_album`
--
ALTER TABLE `ws_album`
  ADD PRIMARY KEY (`id`),
  ADD KEY `login_user` (`login_user`);

--
-- Index pour la table `ws_images`
--
ALTER TABLE `ws_images`
  ADD PRIMARY KEY (`id`),
  ADD KEY `album` (`album`);

--
-- Index pour la table `ws_utilisateurs`
--
ALTER TABLE `ws_utilisateurs`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `login` (`login`);

--
-- AUTO_INCREMENT pour les tables exportées
--

--
-- AUTO_INCREMENT pour la table `ws_album`
--
ALTER TABLE `ws_album`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;
--
-- AUTO_INCREMENT pour la table `ws_images`
--
ALTER TABLE `ws_images`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;
--
-- AUTO_INCREMENT pour la table `ws_utilisateurs`
--
ALTER TABLE `ws_utilisateurs`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;
--
-- Contraintes pour les tables exportées
--

--
-- Contraintes pour la table `ws_album`
--
ALTER TABLE `ws_album`
  ADD CONSTRAINT `ws_album_ibfk_1` FOREIGN KEY (`login_user`) REFERENCES `ws_utilisateurs` (`login`);

--
-- Contraintes pour la table `ws_images`
--
ALTER TABLE `ws_images`
  ADD CONSTRAINT `ws_images_ibfk_1` FOREIGN KEY (`album`) REFERENCES `ws_album` (`id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
