-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Хост: 127.0.0.1
-- Время создания: Апр 11 2016 г., 23:18
-- Версия сервера: 10.1.9-MariaDB
-- Версия PHP: 5.6.15

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- База данных: `mesofusers`
--

-- --------------------------------------------------------

--
-- Структура таблицы `messages`
--

CREATE TABLE `messages` (
  `id` int(11) NOT NULL,
  `username` varchar(50) NOT NULL,
  `message` varchar(255) NOT NULL,
  `dt` datetime NOT NULL,
  `followers` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `messages`
--

INSERT INTO `messages` (`id`, `username`, `message`, `dt`, `followers`) VALUES
(1, 'Alex', 'Hello everybody!', '2016-04-04 05:09:16', 'Mike, Alice, John'),
(2, 'Mike', 'What''s up?', '2015-11-17 10:14:40', 'Alex, Stive, Richard, Alice'),
(3, 'Alice', 'I have a big java cup', '2016-04-22 06:03:21', 'Alex, Mike, John, Stive, Richard'),
(4, 'Richard', 'My name is Riki!', '2015-12-07 06:51:21', 'Alice'),
(5, 'John', 'The Earth id cery small planet...', '2016-08-24 06:37:18', 'Alex, Stive'),
(6, 'Stive', 'Who wants one more bottle beer?', '2016-04-28 05:22:40', 'Alex, Mike, Alice, Richard');

--
-- Индексы сохранённых таблиц
--

--
-- Индексы таблицы `messages`
--
ALTER TABLE `messages`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT для сохранённых таблиц
--

--
-- AUTO_INCREMENT для таблицы `messages`
--
ALTER TABLE `messages`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
