DROP TABLE IF EXISTS `press_article`;
DROP TABLE IF EXISTS `author`;
DROP TABLE IF EXISTS `article_content`;

CREATE TABLE `author`
(
    `id`         BIGINT       NOT NULL AUTO_INCREMENT,
    `first_name` VARCHAR(100) NULL,
    `last_name`  VARCHAR(100) NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `article_content`
(
    `id`      BIGINT       NOT NULL AUTO_INCREMENT,
    `title`   VARCHAR(100) NULL,
    `content` VARCHAR(800) NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `press_article`
(
    `id`                 BIGINT       NOT NULL AUTO_INCREMENT,
    `name`               VARCHAR(100) NULL,
    `publication_date`   TIMESTAMP NULL,
    `save_date`          DATETIME,
    `article_content_id` BIGINT         NOT NULL,
    `author_id`          BIGINT         NOT NULL,
    PRIMARY KEY (`id`),
    CONSTRAINT `fk_press_article_content`
        FOREIGN KEY (`article_content_id`)
            REFERENCES `article_content` (`id`)
            ON DELETE CASCADE
            ON UPDATE CASCADE,
    CONSTRAINT `fk_press_article_author`
        FOREIGN KEY (`author_id`)
            REFERENCES `author` (`id`)
            ON DELETE CASCADE
            ON UPDATE CASCADE
);


