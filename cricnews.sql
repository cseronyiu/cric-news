
CREATE SCHEMA `cricknews` ;


CREATE TABLE `news` (
  `id` bigint NOT NULL,
  `title` varchar(500) COLLATE utf8_unicode_ci DEFAULT NULL,
  `link` varchar(500) COLLATE utf8_unicode_ci DEFAULT NULL,
  `description` varchar(500) COLLATE utf8_unicode_ci DEFAULT NULL,
  `guid` varchar(500) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8_unicode_ci;

