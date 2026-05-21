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

            if (!articles[i].nom.equals("Formatge Gidurat")
                    && !articles[i].nom.equals("Entrades per al Concert del Trobador")) {

                if (articles[i].qualitat > QUALITAT_MINIMA) {

                    if (!articles[i].nom.equals("Martell de Thor (Llegendari)")) {
                        articles[i].qualitat = articles[i].qualitat - 1;
                    }
                }

            } else {

                if (articles[i].qualitat < MAX_QUALITAT) {

                    articles[i].qualitat = articles[i].qualitat + 1;

                    if (articles[i].nom.equals("Entrades per al Concert del Trobador")) {

                        if (articles[i].diesPerVendre <= LIMIT_ENTRADES_10_DIES) {

                            if (articles[i].qualitat < MAX_QUALITAT) {
                                articles[i].qualitat = articles[i].qualitat + 1;
                            }
                        }

                        if (articles[i].diesPerVendre <= LIMIT_ENTRADES_5_DIES) {

                            if (articles[i].qualitat < MAX_QUALITAT) {
                                articles[i].qualitat = articles[i].qualitat + 1;
                            }
                        }
                    }
                }
            }

            if (!articles[i].nom.equals("Martell de Thor (Llegendari)")) {
                articles[i].diesPerVendre = articles[i].diesPerVendre - 1;
            }

            if (articles[i].diesPerVendre < 0) {

                if (!articles[i].nom.equals("Formatge Gidurat")) {

                    if (!articles[i].nom.equals("Entrades per al Concert del Trobador")) {

                        if (articles[i].qualitat > QUALITAT_MINIMA) {

                            if (!articles[i].nom.equals("Martell de Thor (Llegendari)")) {
                                articles[i].qualitat = articles[i].qualitat - 1;
                            }
                        }

                    } else {

                        articles[i].qualitat =
                                articles[i].qualitat - articles[i].qualitat;
                    }

                } else {

                    if (articles[i].qualitat < MAX_QUALITAT) {
                        articles[i].qualitat = articles[i].qualitat + 1;
                    }
                }
            }
        }
    }
}g