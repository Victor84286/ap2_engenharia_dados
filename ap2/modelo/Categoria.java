package modelo;

public enum Categoria {
    Axe(1), Blues(2), Country(3), Eletrônica(4), Forró(5), Funk(6), Gospel(7), Hip_Hop(8), Jazz(9), MPB(10), Música_clássica(11), Pagode(12), Pop(13), Rap(14), Reggae(15), Rock(16), Samba(17);

    public int valor;

    Categoria(int valor){
        this.valor = valor;
    }
}
