/**
my own solu */
class ParkingSystem {
    int[] bigPark;
    int[] mediumPark;
    int[] smallPark;
    int bIndex;
    int mIndex;
    int sIndex;

    public ParkingSystem(int big, int medium, int small) {
        bigPark = new int[big];
        mediumPark = new int[medium];
        smallPark = new int[small];
        bIndex = 0;
        mIndex = 0;
        sIndex = 0;
    }
    
    public boolean addCar(int carType) {
        if (carType == 1) {
            if (bIndex == bigPark.length) return false;
            bIndex++;
        } else if (carType == 2) {
            if (mIndex == mediumPark.length) return false;
            mIndex++;
        } else {
            if (sIndex == smallPark.length) return false;
            sIndex++;
        }
        return true;
    }
}

/**
optimal solu */
class ParkingSystem {
    int[] park;
    
    public ParkingSystem(int big, int medium, int small) {
        park = new int[] {big, medium, small};
    }
    
    public boolean addCar(int carType) {
        return park[carType-1]-- > 0;
    }
}