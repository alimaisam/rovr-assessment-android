package com.alimaisam.githubsearch.Helper

object EndPoints {
    private const val URL_ROOT = "https://f285480b.ngrok.io"
    const val URL_SEARCH_BY_LANGUAGE = "$URL_ROOT/search/repo/language?keyword="
    const val URL_SEARCH_BY_TOPIC = "$URL_ROOT/search/repo/topic?keyword="
    const val URL_REPORT = "$URL_ROOT/admin/report"
}