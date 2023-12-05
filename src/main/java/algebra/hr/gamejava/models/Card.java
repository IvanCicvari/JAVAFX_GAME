package algebra.hr.gamejava.models;

public class Card {
    public enum ValueOfCard
    {
        Zero,One,Two,Three,Four;
        private static final ValueOfCard[] value = ValueOfCard.values();
        public static ValueOfCard getValue(int i)
        {
            return ValueOfCard.value[i];
        }
    }
    public enum Country
    {
        USA,USSR;
        private static final Country[] countries = Country.values();
        public static Country getCountry(int i)
        {
            return Country.countries[i];
        }
    }



    private final ValueOfCard valueOfCard;
    private final Country country;

    public Card(final ValueOfCard valueOfCard, final Country country) {
        this.valueOfCard = valueOfCard;
        this.country = country;
    }

    public ValueOfCard getValueOfCard() {
        return this.valueOfCard;
    }

    public Country getCountry() {
        return this.country;
    }

    @Override
    public String toString() {
        return "Card{" +
                "valueOfCard=" + valueOfCard +
                ", country=" + country +
                '}';
    }

}
