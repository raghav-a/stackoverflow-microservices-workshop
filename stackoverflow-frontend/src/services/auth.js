export const login = async (userId, password) => {
    const response = await fetch('http://localhost:8765/api/sessions/login', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({ userId, password })
    });

    return response.json();
};


/*
const divide = (x, y) => new Promise((resolve, reject) => {
    if (y === 0) {
        reject(new Error('Can\'t divide by zero'));
    } else {
        resolve(x + y);
    }
});

const divide = async (x, y) => {
    if (y === 0) throw new Error('Can\'t divide by zero');
    return x + y;
}

divide(1, 2).then(result => console.log(result));

const makeAPICall = () => { // returns a promise
    return fetch(...).then(response => response.json());
};

const makeAPICall = async () => {
    const respomse = await fetch(...);
    return response.json();
}


makeAPICall().then(json => console.log(json));
console.log('hello world');

const num = 4;
const arr = [4];
const obj = { foo: 'bar', 2: 4 }

const double = val => val * 2;
const propFromObject = key => key in obj ? Just(obj[key]) : None;
arr.map(double);

const None = {
    map: () => None
};

const Just = val => {
    map: fn => Just(fn(val))
};

const divide = (x, y) => {
    if (y === 0) return None;
    return Just(x / y);
};

divide(4, 2).map(double);
divide(4, 2).flatMap(propFromObject); */
