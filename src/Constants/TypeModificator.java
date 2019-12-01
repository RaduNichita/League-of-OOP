//package Constants;
//
//public class TypeModificator {
//
//    private static int RogueModificator;
//    private static int KnightModificator;
//    private static int WizardModificator;
//    private static int PyromancerModificator;
//
//    private TypeModificator() {
//
//    }
//    public static class Builder {
//        private int RogueModificator;
//        private int KnightModificator;
//        private int WizardModificator;
//        private int PyromancerModificator;
//
//        public Builder vsRogue(final int RogueModificator){
//            this.RogueModificator = RogueModificator;
//            return this;
//        }
//
//        public Builder vsKnight(final int KnightModificator) {
//            this.KnightModificator = KnightModificator;
//            return this;
//        }
//
//        public Builder vsWizard(final int WizardModificator) {
//            this.WizardModificator = WizardModificator;
//            return this;
//        }
//
//        public Builder vsPyromancer(final int PyromancerModificator) {
//            this.PyromancerModificator = PyromancerModificator;
//            return this;
//        }
//
//        public TypeModificator build() {
//            TypeModificator typeModificator = new TypeModificator();
//
//            TypeModificator.RogueModificator = this.RogueModificator;
//            TypeModificator.PyromancerModificator = this.PyromancerModificator;
//            TypeModificator.WizardModificator = this.WizardModificator;
//            TypeModificator.KnightModificator = this.KnightModificator;
//
//            return  typeModificator;
//        }
//    }
//
//}
