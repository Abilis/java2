-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Хост: 127.0.0.1
-- Время создания: Апр 22 2016 г., 08:03
-- Версия сервера: 10.1.9-MariaDB
-- Версия PHP: 5.6.15

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- База данных: `finmanager`
--
CREATE DATABASE IF NOT EXISTS `finmanager` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `finmanager`;

-- --------------------------------------------------------

--
-- Структура таблицы `accounts`
--

CREATE TABLE IF NOT EXISTS `accounts` (
  `id_acc` int(11) NOT NULL AUTO_INCREMENT,
  `description` varchar(50) NOT NULL,
  `ostatok` int(11) NOT NULL,
  `id_user` int(11) NOT NULL,
  PRIMARY KEY (`id_acc`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `accounts`
--

INSERT INTO `accounts` (`id_acc`, `description`, `ostatok`, `id_user`) VALUES
(1, 'Главный аккаунт', 1001, 9),
(2, 'Второй тестовый аккаунт', 3000, 9),
(3, 'Третий аккаунт', 3912, 9),
(4, 'Первый аккаунт', 1500, 18),
(5, 'Второй акк', 500, 18),
(6, 'acc for test2', 222, 19),
(7, 'acc for test5', 7002, 20);

-- --------------------------------------------------------

--
-- Структура таблицы `records`
--

CREATE TABLE IF NOT EXISTS `records` (
  `id_rec` int(11) NOT NULL AUTO_INCREMENT,
  `label` tinyint(1) NOT NULL,
  `dt` varchar(30) NOT NULL,
  `sum` int(11) NOT NULL,
  `description` varchar(150) NOT NULL,
  `category` varchar(100) NOT NULL,
  `id_acc` int(11) NOT NULL,
  PRIMARY KEY (`id_rec`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `records`
--

INSERT INTO `records` (`id_rec`, `label`, `dt`, `sum`, `description`, `category`, `id_acc`) VALUES
(1, 0, '2016-04-21 09:22:53', 50, 'Мороженка', 'FOOD', 1),
(2, 1, '2016-04-21 12:17:19', 1000, 'На здоровье!', 'HEALTH', 1),
(3, 0, '2016-04-21 12:17:31', 120, 'Гамбургер', 'FOOD', 1),
(4, 0, '2016-04-21 22:23:09', 50, 'Еще мороженка', 'FOOD', 1),
(5, 1, '2016-04-21 10:00:00', 333, 'На мороженки!', 'FOOD', 1),
(6, 0, '2016-04-21 12:31:47', 113, 'Лишние', 'OTHER', 1),
(7, 1, '2016-04-21 13:09:41', 222, 'Тестовое пополнение', 'TRAVELLING', 2),
(8, 0, '2016-04-21 13:21:16', 222, 'Какое-то описание', 'OTHER', 2),
(9, 0, '2016-04-21 13:25:43', 1500, 'Бутылка водки', 'HEALTH', 3),
(10, 0, '2016-04-21 13:37:59', 88, 'Пыво!', 'HEALTH', 3),
(11, 1, '2016-04-21 14:03:26', 500, 'Пополнение на 500', 'HEALTH', 4),
(12, 0, '2016-04-21 14:12:07', 777, 'Тест', 'OTHER', 6),
(13, 1, '2016-04-21 14:13:55', 336, 'Тест', 'OTHER', 7),
(14, 0, '2015-04-23 10:00:37', 999, 'Шарфег', 'CLOTHES', 1);

-- --------------------------------------------------------

--
-- Структура таблицы `users`
--

CREATE TABLE IF NOT EXISTS `users` (
  `id_user` int(11) NOT NULL AUTO_INCREMENT,
  `login` varchar(25) NOT NULL,
  `password` varchar(32) NOT NULL,
  PRIMARY KEY (`id_user`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `users`
--

INSERT INTO `users` (`id_user`, `login`, `password`) VALUES
(9, 'user', '1a1dc91c907325c69271ddf0c944bc72'),
(18, 'test', '098f6bcd4621d373cade4e832627b4f6'),
(19, 'test2', 'ad0234829205b9033196ba818f7a872b'),
(20, 'test5', 'e3d704f3542b44a621ebed70dc0efe13');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
