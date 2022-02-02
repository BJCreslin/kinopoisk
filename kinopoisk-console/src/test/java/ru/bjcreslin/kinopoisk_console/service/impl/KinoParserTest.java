package ru.bjcreslin.kinopoisk_console.service.impl;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class KinoParserTest {

    private final KinoParser kinoParser=new KinoParser();

    private final String htmlRu="<div class=\"desktop-rating-selection-film-item\" data-tid=\"af76fbb3\"><div class=\"desktop-rating-selection-film-item__rating-position-wrapper\"><div class=\"film-item-rating-position\" data-tid=\"6a4a86b0\"><span class=\"film-item-rating-position__position\">12</span><span class=\"film-item-rating-position__diff film-item-rating-position__diff_sign_neutral\">0</span></div></div><a class=\"selection-film-item-poster selection-film-item-poster_play-button\" data-tid=\"9b39d766\"><img class=\"selection-film-item-poster__image image-partial-component _12xtW0ru9JBdp6H6D23foa CIAcnTIOMz_cEIhVn6aUO\" alt=\"Иван Васильевич меняет профессию\" src=\"//avatars.mds.yandex.net/get-kinopoisk-image/1704946/1a170eea-da02-40c1-a750-91c59d56e1a6/68x102\" srcset=\"//avatars.mds.yandex.net/get-kinopoisk-image/1704946/1a170eea-da02-40c1-a750-91c59d56e1a6/68x102 1x, //avatars.mds.yandex.net/get-kinopoisk-image/1704946/1a170eea-da02-40c1-a750-91c59d56e1a6/136x204 2x\" data-tid=\"d813cf42\"><span class=\"selection-film-item-poster__rating selection-film-item-poster__rating_positive\">8.8</span></a><div class=\"desktop-rating-selection-film-item__content-wrapper\"><div class=\"desktop-rating-selection-film-item__upper-wrapper\"><div class=\"desktop-rating-selection-film-item__meta-wrapper\"><div class=\"selection-film-item-meta selection-film-item-meta_theme_desktop\" data-tid=\"6cf86878\"><a href=\"/film/42664/\" class=\"selection-film-item-meta__link\"><p class=\"selection-film-item-meta__name\">Иван Васильевич меняет профессию</p><p class=\"selection-film-item-meta__original-name\">1973</p><p class=\"selection-film-item-meta__meta-additional\"><span class=\"selection-film-item-meta__meta-additional-item\">СССР</span><span class=\"selection-film-item-meta__meta-additional-item\">комедия, фантастика</span></p></a></div></div><div class=\"desktop-rating-selection-film-item__user-wrapper\"><div class=\"film-item-user-data\" data-tid=\"163b93b5\"><span class=\"rating film-item-user-data__rating\" data-tid=\"facad451\"><span class=\"rating__value rating__value_positive\" data-tid=\"59e7b831\">8.7</span><span class=\"rating__count\" data-tid=\"59e7b831\">741 224</span></span><div class=\"film-item-user-data__controls\" data-tid=\"7a8e3340\"><div class=\"watching-buttons film-item-user-data__watching\" data-tid=\"73f52974\"><div class=\"ui-button-group watching-buttons__group\" data-tid=\"218f4a8d\"><button class=\"ui-button ui-button_size_xs ui-button_kind_ghost watching-buttons__will-watch\" kind=\"ghost\" target=\"_self\"><svg width=\"14\" height=\"14\" viewBox=\"0 0 14 14\"><path fill=\"rgba(0, 0, 0, 0.6)\" fill-rule=\"evenodd\" d=\"M9.8 3.2H4.2v7.858l2.8-1.4 2.8 1.4V3.2zM3 2h8v11l-4-2-4 2V2z\"></path></svg><span class=\"watching-buttons__will-watch-caption\" data-tid=\"4c94c8b1\">Буду смотреть</span></button><button class=\"ui-button ui-button_size_xs ui-button_kind_ghost watching-buttons__has-watched\" kind=\"ghost\" target=\"_self\"><svg width=\"14\" height=\"14\" viewBox=\"0 0 14 14\"><path fill=\"rgba(0, 0, 0, 0.6)\" fill-rule=\"evenodd\" d=\"M8.8 3.999A5.332 5.332 0 0 0 7 3.7c-.637 0-1.237.098-1.8.299V5.5a1.8 1.8 0 1 0 3.6 0V3.999zm1.2.618V5.5a3 3 0 1 1-6 0v-.883C3.215 5.16 2.511 5.95 1.879 7 3.23 9.244 4.91 10.3 7 10.3c2.089 0 3.77-1.056 5.121-3.3C11.49 5.95 10.785 5.16 10 4.617zM7 11.5C4.267 11.5 2.1 10 .5 7 2.1 4 4.267 2.5 7 2.5S11.9 4 13.5 7c-1.6 3-3.767 4.5-6.5 4.5z\"></path></svg></button></div></div><div class=\"voting orP4zfEYohU5yUqh3kRtK film-item-user-data__voting\" data-tid=\"1eee1326\"><button type=\"button\" class=\"voting__closed _1DGZl_MljxzkNSjxYYB3YB V5CIPvMrirnxs1Bm05smr\" data-tid=\"73e27f8a\"><i class=\"voting__star _2ALhTFBPdh_IVbB-VWHXHn _2r5gNlv9oQw_0Z0_Kc2GyR\"></i></button></div></div><div class=\"film-item-context-menu film-item-user-data__context-menu\" data-tid=\"ea119547\"><div class=\"film-item-context-menu__button\" data-tid=\"a15168b1\"></div></div></div></div></div><div class=\"desktop-rating-selection-film-item__bottom-wrapper\"><div class=\"film-item-buy-buttons\" data-tid=\"50aada8e\"><div class=\"film-item-buy-buttons__online-link-wrapper\"><div class=\"online-block\" data-tid=\"cac4991f\"><a class=\"HaRNLxUnsJBznTziWnJLc _2uafN1nqLS4lOOw1XcCR1F _2Rr4W29sDB8Q8CY5WPzzN3 _3J1Gj9qhiOSA9zzTzU0k5W\" href=\"/film/42664/watch/?from_block=kp-button-list\"><span class=\"_2Or2tSu-DA2pPjcAuVmgCh Whjy4mRGeDV50CJOANc1y\" data-tid=\"63c3b8c4\"></span>По подписке Плюс</a></div></div></div></div></div></div>";

    @Test
    void shouldGetOriginalName(){
        Element element= Jsoup.parseBodyFragment(htmlRu).root();
        Assertions.assertNotNull(kinoParser.getOriginalName(element));
        Assertions.assertEquals("Иван Васильевич меняет профессию", kinoParser.getOriginalName(element));
    }
}