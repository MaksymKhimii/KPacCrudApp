DROP TABLE IF EXISTS `knowledge_package`.`k-pac`;
CREATE TABLE `knowledge_package`.`k-pac`
(
    `k_pac_id`      INT           NOT NULL AUTO_INCREMENT,
    `title`         VARCHAR(250)  NOT NULL,
    `description`   VARCHAR(2000) NOT NULL,
    `creation_date` DATE          NOT NULL,
    UNIQUE INDEX `idkpac_UNIQUE` (`k_pac_id` ASC) VISIBLE,
    PRIMARY KEY (`k_pac_id`)
);


DROP TABLE IF EXISTS `knowledge_package`.`k-pac_set`;
CREATE TABLE `knowledge_package`.`k-pac_set`
(
    `k_pac_set_id` INT          NOT NULL AUTO_INCREMENT,
    `set_title`    VARCHAR(250) NOT NULL,
    PRIMARY KEY (`k_pac_set_id`),
    UNIQUE INDEX `idk-pac_set_UNIQUE` (`k_pac_set_id` ASC) VISIBLE
);

DROP TABLE IF EXISTS `knowledge_package`.`k-link`;
CREATE TABLE `knowledge_package`.`k-link`
(
    `k_link_id`    INT AUTO_INCREMENT NOT NULL,
    `k_pac_id`     INT NULL,
    `k_pac_set_id` INT NULL,
    PRIMARY KEY (`k_link_id`),
    UNIQUE INDEX `k-link_id_UNIQUE` (`k_link_id` ASC) VISIBLE
);