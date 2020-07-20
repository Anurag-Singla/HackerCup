import array

class ProblemA:
    num_tests = 0
    squares = None
    len_squares = None

    def __init__(self, num_tests):
        self.num_tests = num_tests
        squares = [x * x for x in range(46341)]
        self.len_squares = len(squares)
        self.squares = squares

    def solve(self, test_case, x: int):
        answer = 0
        i = 0
        j = self.len_squares - 1
        while i <= j:
            sum_squares = self.squares[i] + self.squares[j]
            if sum_squares == x:
                answer += 1
                i = i + 1
                j = j - 1
            elif sum_squares > x:
                j = j - 1
            else:
                i = i + 1

        print("Case #" + str(test_case) + ": " + str(answer))


def main():
    n = int(input())
    problem_a = ProblemA(n)
    for i in range(n):
        x = int(input())
        problem_a.solve(i + 1, x)


if __name__ == "__main__":
    main()
