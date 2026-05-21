class Magatzem {

    private static final int MAX_QUALITAT = 50;
    private static final int QUALITAT_MINIMA = 0;
    private static final int LIMIT_ENTRADES_10_DIES = 10;
    private static final int LIMIT_ENTRADES_5_DIES = 5;

    Article[] articles;

    public Magatzem(Article[] articles) {
        this.articles = articles;
    }

    public void actualitzarEstat() {

        for (Article article : articles) {

            if (esLegendari(article)) {
                continue;
            }

            actualitzarQualitat(article);
            article.diesPerVendre--;

            if (estaCaducat(article)) {
                aplicarCaducitat(article);
            }
        }
    }

    // 🔥 EXTRACT VARIABLE
    private boolean estaCaducat(Article article) {
        return article.diesPerVendre < 0;
    }

    private void actualitzarQualitat(Article article) {

        if (esFormatge(article)) {
            augmentarQualitatSiPossible(article);
            return;
        }

        if (esEntrada(article)) {
            actualitzarEntrades(article);
            return;
        }

        degradarNormal(article);
    }

    private void augmentarQualitatSiPossible(Article article) {
        if (article.qualitat < MAX_QUALITAT) {
            article.qualitat++;
        }
    }

    private void actualitzarEntrades(Article article) {

        if (article.qualitat < MAX_QUALITAT) {

            article.qualitat++;

            boolean periodeAlt = article.diesPerVendre <= LIMIT_ENTRADES_10_DIES;
            boolean periodeBaix = article.diesPerVendre <= LIMIT_ENTRADES_5_DIES;

            if (periodeAlt && article.qualitat < MAX_QUALITAT) {
                article.qualitat++;
            }

            if (periodeBaix && article.qualitat < MAX_QUALITAT) {
                article.qualitat++;
            }
        }
    }

    private void degradarNormal(Article article) {
        if (article.qualitat > QUALITAT_MINIMA) {
            article.qualitat--;
        }
    }

    private void aplicarCaducitat(Article article) {

        if (esFormatge(article)) {
            augmentarQualitatSiPossible(article);
            return;
        }

        if (esEntrada(article)) {
            article.qualitat = 0;
            return;
        }

        if (article.qualitat > QUALITAT_MINIMA) {
            article.qualitat--;
        }
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
}