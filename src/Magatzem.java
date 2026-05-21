class Magatzem {

    private static final int MAX_QUALITAT = 50;
    private static final int QUALITAT_LEGENDARIA = 80;
    private static final int LIMIT_ENTRADES_10_DIES = 10;
    private static final int LIMIT_ENTRADES_5_DIES = 5;
    private static final int QUALITAT_MINIMA = 0;

    Article[] articles;

    public Magatzem(Article[] articles) {
        this.articles = articles;
    }

    public void actualitzarEstat() {

        for (int i = 0; i < articles.length; i++) {

            Article article = articles[i];

            boolean esFormatge =
                    article.nom.equals("Formatge Gidurat");

            boolean esEntrada =
                    article.nom.equals("Entrades per al Concert del Trobador");

            boolean esLegendari =
                    article.nom.equals("Martell de Thor (Llegendari)");

            if (!esFormatge && !esEntrada) {

                if (article.qualitat > QUALITAT_MINIMA) {

                    if (!esLegendari) {
                        article.qualitat = article.qualitat - 1;
                    }
                }

            } else {

                if (article.qualitat < MAX_QUALITAT) {

                    article.qualitat = article.qualitat + 1;

                    if (esEntrada) {

                        if (article.diesPerVendre <= LIMIT_ENTRADES_10_DIES) {

                            if (article.qualitat < MAX_QUALITAT) {
                                article.qualitat = article.qualitat + 1;
                            }
                        }

                        if (article.diesPerVendre <= LIMIT_ENTRADES_5_DIES) {

                            if (article.qualitat < MAX_QUALITAT) {
                                article.qualitat = article.qualitat + 1;
                            }
                        }
                    }
                }
            }

            if (!esLegendari) {
                article.diesPerVendre = article.diesPerVendre - 1;
            }

            if (article.diesPerVendre < 0) {

                if (!esFormatge) {

                    if (!esEntrada) {

                        if (article.qualitat > QUALITAT_MINIMA) {

                            if (!esLegendari) {
                                article.qualitat = article.qualitat - 1;
                            }
                        }

                    } else {

                        article.qualitat =
                                article.qualitat - article.qualitat;
                    }

                } else {

                    if (article.qualitat < MAX_QUALITAT) {
                        article.qualitat = article.qualitat + 1;
                    }
                }
            }
        }
    }
}