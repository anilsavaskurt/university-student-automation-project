-- phpMyAdmin SQL Dump
-- version 4.9.1
-- https://www.phpmyadmin.net/
--
-- Anamakine: 127.0.0.1
-- Üretim Zamanı: 04 Şub 2020, 12:36:08
-- Sunucu sürümü: 10.4.8-MariaDB
-- PHP Sürümü: 7.3.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Veritabanı: `proje`
--

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `bolumler`
--

CREATE TABLE `bolumler` (
  `bid` int(10) NOT NULL,
  `badi` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Tablo döküm verisi `bolumler`
--

INSERT INTO `bolumler` (`bid`, `badi`) VALUES
(1, 'Bilgisayar'),
(2, 'Elektrik'),
(3, 'Endustri');

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `dersler`
--

CREATE TABLE `dersler` (
  `did` int(4) NOT NULL,
  `dadi` varchar(30) NOT NULL,
  `dgunu` varchar(20) DEFAULT NULL,
  `dbaslamasaati` time DEFAULT NULL,
  `dbitissaati` time DEFAULT NULL,
  `bid` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Tablo döküm verisi `dersler`
--

INSERT INTO `dersler` (`did`, `dadi`, `dgunu`, `dbaslamasaati`, `dbitissaati`, `bid`) VALUES
(1546, 'Calculus3', 'SALI', '13:00:00', '17:00:00', 2),
(2571, 'NYP', 'SALI', '09:00:00', '11:00:00', 1),
(4444, 'Elektronik', 'CUMA', '10:00:00', '11:45:00', 2),
(5256, 'Ekonomi', NULL, NULL, NULL, 3),
(5599, 'Muhasebe', NULL, NULL, NULL, 3),
(6547, 'Yapay Zeka', NULL, NULL, NULL, 1),
(7896, 'Devreler', NULL, NULL, NULL, 2),
(7956, 'EMG', NULL, NULL, NULL, 3),
(8888, 'Olasılık', NULL, NULL, NULL, 3),
(9999, 'Algoritmalar', 'CARSAMBA', '07:00:00', '10:50:00', 1);

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `idarimemur`
--

CREATE TABLE `idarimemur` (
  `id` int(10) NOT NULL,
  `ad` varchar(20) NOT NULL,
  `soyad` varchar(20) NOT NULL,
  `sifre` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Tablo döküm verisi `idarimemur`
--

INSERT INTO `idarimemur` (`id`, `ad`, `soyad`, `sifre`) VALUES
(1, 'Tolga', 'Müdür', '1');

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `ogrenci`
--

CREATE TABLE `ogrenci` (
  `id` int(10) NOT NULL,
  `ad` varchar(20) NOT NULL,
  `soyad` varchar(20) NOT NULL,
  `bolum` varchar(30) NOT NULL,
  `bid` int(10) NOT NULL,
  `sifre` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Tablo döküm verisi `ogrenci`
--

INSERT INTO `ogrenci` (`id`, `ad`, `soyad`, `bolum`, `bid`, `sifre`) VALUES
(1306170002, 'Anıl', 'Savaşkurt', 'Bilgisayar', 1, '1'),
(1306170003, 'Çağı', 'Şahin', 'Bilgisayar', 1, 'cgr123'),
(1306170041, 'Umut', 'Özat', 'Bilgisayar', 1, '9876umut'),
(1306170101, 'Gizem', 'Yaren', '3', 3, '123'),
(1306170544, 'Ömer', 'Kahraman', 'Elektrik', 2, 'khrmn1907');

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `ogrenci_ders`
--

CREATE TABLE `ogrenci_ders` (
  `id` int(10) NOT NULL,
  `did` int(10) NOT NULL,
  `dnotu` int(3) DEFAULT -1,
  `devamsizlik` int(1) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Tablo döküm verisi `ogrenci_ders`
--

INSERT INTO `ogrenci_ders` (`id`, `did`, `dnotu`, `devamsizlik`) VALUES
(1306170002, 2571, 90, 5),
(1306170003, 2571, 85, 3),
(1306170041, 9999, -1, 0),
(1306170544, 4444, -1, 0),
(1306170002, 9999, 60, 0),
(1306170002, 6547, -1, 0),
(1306170101, 5599, -1, 0);

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `ogretimgorevlisi`
--

CREATE TABLE `ogretimgorevlisi` (
  `id` int(10) NOT NULL,
  `ad` varchar(20) NOT NULL,
  `soyad` varchar(20) NOT NULL,
  `verdigiders` varchar(30) NOT NULL,
  `did` int(10) NOT NULL,
  `sifre` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Tablo döküm verisi `ogretimgorevlisi`
--

INSERT INTO `ogretimgorevlisi` (`id`, `ad`, `soyad`, `verdigiders`, `did`, `sifre`) VALUES
(1, 'Merve', 'Girgin', 'NYP', 2571, '1'),
(1659487, 'MAHMUT', 'TUNCER', 'Muhasebe', 5599, '12345677'),
(232356454, 'Murat', 'Avcılar', 'Calculus3', 1546, 'murat131'),
(326545987, 'Ahmet', 'Kurt', 'Algoritmalar', 9999, 'ahmtkurt99'),
(445454545, 'Uğur', 'Özcan', 'Elektronik', 4444, 'ozcanugur1'),
(566987981, 'Mert', 'Ayyıldız', 'EMG', 7956, 'ayyldz123'),
(777777777, 'Pınar', 'Akansu', 'Ekonomi', 5256, 'pınar1010'),
(849161321, 'Defne', 'Yurt', 'Olasılık', 8888, 'defne1919'),
(1000000000, 'Oguz', 'Berk', 'Yapay Zeka', 6547, 'oguz112');

--
-- Dökümü yapılmış tablolar için indeksler
--

--
-- Tablo için indeksler `bolumler`
--
ALTER TABLE `bolumler`
  ADD PRIMARY KEY (`bid`);

--
-- Tablo için indeksler `dersler`
--
ALTER TABLE `dersler`
  ADD PRIMARY KEY (`did`),
  ADD KEY `bid` (`bid`),
  ADD KEY `dadi` (`dadi`);

--
-- Tablo için indeksler `idarimemur`
--
ALTER TABLE `idarimemur`
  ADD PRIMARY KEY (`id`);

--
-- Tablo için indeksler `ogrenci`
--
ALTER TABLE `ogrenci`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `id` (`id`),
  ADD KEY `bid` (`bid`);

--
-- Tablo için indeksler `ogrenci_ders`
--
ALTER TABLE `ogrenci_ders`
  ADD KEY `did` (`did`),
  ADD KEY `id` (`id`);

--
-- Tablo için indeksler `ogretimgorevlisi`
--
ALTER TABLE `ogretimgorevlisi`
  ADD PRIMARY KEY (`id`),
  ADD KEY `did` (`did`),
  ADD KEY `verdigiders` (`verdigiders`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
