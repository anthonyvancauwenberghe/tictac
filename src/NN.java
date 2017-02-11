import java.util.ArrayList;

/**
 * Created by pmmde on 11-Feb-17.
 */
public class NN {

    /**
     * idee voor NN training:
     * Je maakt 2 NN met 18 inputs en 9 outputs (random)
     * Je laat die een potje tegen elkaar spelen
     * Je doet de speler die wint al zijn stappen aan zn training set toevoegen, en je doet backprop opniew
     * En dan laat je ze weer tegen elkaar spelen
     */

    double scaler;
    double lambda;

    ArrayList<Matrix> weights;
    ArrayList<Matrix> weightsChange;
    ArrayList<Matrix> z;
    ArrayList<Matrix> a;

    ArrayList<Integer> layers;

    double lastCalculatedCost;

    boolean preDataChanged = true;
    ArrayList<ArrayList<Float>> preInput;
    ArrayList<ArrayList<Float>> preOutput;
    Matrix input;
    Matrix output;

    NN(ArrayList<Integer> l) {
        for(int i = 0; i < (l.size() - 1); i++) {
            Matrix m(l[i], l[i + 1]);
            m.setRandom();
            weights.add(m);
        }
        layers = l;
        scaler = 1.0f;
        lambda = 0.0001;
        lastCalculatedCost = 0;
    }

    boolean addData(vector<float> i, vector<float> o) {
        if (i.size() == layers[0] && o.size() == layers[layers.size() - 1]) {
            preInput.push_back(i);
            preOutput.push_back(o);
            preDataChanged = true;
            return true;
        }
        return false;
    }
    void resetData() {
        preInput.clear();
        preOutput.clear();
        preDataChanged = true;
    }
    void train(int iterations) {
        if (preDataChanged) {
            input = Matrix(preInput);
            output = Matrix(preOutput);
            preDataChanged = false;
        }
        for (int i = 0; i < iterations; i++) {
            train(input, output);
        }
    }
    ArrayList<Float> predict(ArrayList<Float> input) {
        return predictM(Matrix({input})).get()[0];
    }
    Matrix predictM(Matrix input)
    {
        a.clear();
        z.clear();
        a.push_back(input);
        z.push_back(input);
        for (int i = 0; i < weights.size(); i++) {
            z.push_back(a.back()*weights.at(i));
            a.push_back(z.back().sigmoid());
        }
        return a.back();
    }
    float calcCost(Matrix input, Matrix expected) {
        Matrix output = predictM(input);
        float squareSumOfWeights = 0;
        for (int i = 0; i < weights.size(); i++) {
            squareSumOfWeights += weights.at(i).square().sum();
        }
        lastCalculatedCost = ((0.5f*(expected - output).square().sum()) / input.getAmountOfRows()) + ((lambda / 2.0f)*squareSumOfWeights);
        return lastCalculatedCost;
    }
    void calcCostPrime(Matrix input, Matrix expected) {
        Matrix output = predictM(input);
        weightsChange.clear();

        Matrix delta = (-(expected - output)).elementWiseMultiplication(z.back().sigmoidPrime());
        weightsChange.insert(weightsChange.begin(), (a.at(a.size() - 2).transpose()*delta) + (weights.back()*lambda));

        for (int i = weights.size() - 1; i >= 1; i--) {
            delta = (delta*(weights.at(i).transpose())).elementWiseMultiplication(z.at(i).sigmoidPrime());
            weightsChange.insert(weightsChange.begin(), (a.at(i - 1).transpose()*delta) + (weights.at(i - 1)*lambda));
        }
    }
    void train(Matrix input, Matrix expected) {
        calcCostPrime(input, expected);
        for (int i = 0; i < weights.size(); i++)
        {
            weights.at(i) = weights.at(i) - weightsChange.at(i)*scaler;
        }
    }
    //This function can be used to check if your nearal network is working
    /*void check(Matrix input, Matrix expected) {
        double e = 1e-4;
        calcCostPrime(input, expected);

        for (int k = 0; k < weights.size(); k++) {
            for (int i = 0; i < weights.at(k).getAmountOfRows(); i++) {
                for (int j = 0; j < weights.at(k).getAmountOfColumns(); j++) {
                    float error = calcCost(input, expected);
                    float original = weights.at(k).getElement(i, j);

                    weights.at(k).setElement(i, j, original - e);
                    float error1 = calcCost(input, expected);

                    weights.at(k).setElement(i, j, original + e);
                    float error2 = calcCost(input, expected);

                    weights.at(k).setElement(i, j, original);

                    float delta = (error2 - error1) / (2 * e);

                    float deltaError = delta - weightsChange.at(k).getElement(i, j);

                    if (deltaError > 1e-2) {
                        cout << "Still room for opimalisation: " << deltaError << endl;
                    }
                }
            }
        }
    }*/
}
