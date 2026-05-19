class Magatzem {

    private static final String FORMATGE = "Formatge Gidurat";
    private static final String ENTRADES = "Entrades per al Concert del Trobador";
    private static final String MARTELL = "Martell de Thor (Llegendari)";

    Article[] articles;

    public Magatzem(Article[] articles) {
        this.articles = articles;
    }

    public void actualitzarEstat() {
        for (Article article : articles) {
            actualitzarArticle(article);
        }
    }

    private void actualitzarArticle(Article article) {

        if (esLegendari(article)) {
            return;
        }

        actualitzarQualitat(article);
        actualitzarDies(article);

        if (article.diesPerVendre < 0) {
            aplicarCaducitat(article);
        }
    }

    private void actualitzarQualitat(Article article) {

        if (esFormatge(article)) {
            if (article.qualitat < 50) {
                article.qualitat++;
            }
            return;
        }

        if (esEntrada(article)) {
            if (article.qualitat < 50) {
                article.qualitat++;

                if (article.diesPerVendre < 11) {
                    article.qualitat++;
                }

                if (article.diesPerVendre < 6) {
                    article.qualitat++;
                }
            }
            return;
        }

        if (article.qualitat > 0) {
            article.qualitat--;
        }
    }

    private void actualitzarDies(Article article) {
        if (!esLegendari(article)) {
            article.diesPerVendre--;
        }
    }

    private void aplicarCaducitat(Article article) {

        if (esFormatge(article)) {
            if (article.qualitat < 50) {
                article.qualitat++;
            }
            return;
        }

        if (esEntrada(article)) {
            article.qualitat = 0;
            return;
        }

        if (article.qualitat > 0) {
            article.qualitat--;
        }
    }

    private boolean esFormatge(Article article) {
        return article.nom.equals(FORMATGE);
    }

    private boolean esEntrada(Article article) {
        return article.nom.equals(ENTRADES);
    }

    private boolean esLegendari(Article article) {
        return article.nom.equals(MARTELL);
    }
}