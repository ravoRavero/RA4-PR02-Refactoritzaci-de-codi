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

        for (Article article : articles) {

            processarArticle(article);
        }
    }

    private void processarArticle(Article article) {

        if (esArticleNormal(article)) {
            degradarArticleNormal(article);
        } else {
            millorarArticle(article);
        }

        if (!esLegendari(article)) {
            article.diesPerVendre--;
        }

        if (estaCaducat(article)) {
            aplicarEfectesCaducitat(article);
        }
    }

    private boolean estaCaducat(Article article) {
        return article.diesPerVendre < 0;
    }

    private void aplicarEfectesCaducitat(Article article) {

        if (esFormatge(article)) {

            if (article.qualitat < MAX_QUALITAT) {
                article.qualitat++;
            }

        } else if (esEntrada(article)) {

            article.qualitat = 0;

        } else {

            if (potDegradar(article)) {
                article.qualitat--;
            }
        }
    }

    private boolean potDegradar(Article article) {
        return article.qualitat > QUALITAT_MINIMA && !esLegendari(article);
    }

    private boolean esArticleNormal(Article article) {
        return !esFormatge(article) && !esEntrada(article);
    }

    private boolean esFormatge(Article article) {
        return article.nom.equals("Formatge Gidurat");
    }

    private boolean esEntrada(Article article) {
        return article.nom.equals("Entrades per al Concert del Trobador");
    }

    private boolean esLegendari(Article article) {
        return article.nom.equals("Martell de Thor (Llegendari)");
    }

    private void degradarArticleNormal(Article article) {

        if (article.qualitat > QUALITAT_MINIMA) {

            if (!esLegendari(article)) {
                article.qualitat--;
            }
        }
    }

    private void millorarArticle(Article article) {

        if (article.qualitat < MAX_QUALITAT) {

            article.qualitat++;

            if (esEntrada(article)) {

                if (article.diesPerVendre <= LIMIT_ENTRADES_10_DIES) {

                    if (article.qualitat < MAX_QUALITAT) {
                        article.qualitat++;
                    }
                }

                if (article.diesPerVendre <= LIMIT_ENTRADES_5_DIES) {

                    if (article.qualitat < MAX_QUALITAT) {
                        article.qualitat++;
                    }
                }
            }
        }
    }
}