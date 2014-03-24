if (!Number.prototype.toFixed) {
    Number.prototype.toFixed = function (num) {
        with (Math)return round(this.valueOf() * pow(10, num)) / pow(10, num);
    }
}