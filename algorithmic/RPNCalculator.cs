using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace algorithmic
{
    public class RPNCalculator
    {
        private Stack<int> memory = new();
        private static Dictionary<string, Func<int, int, int>> operators = new() {
            {"+", (a, b) => a + b},
            {"-", (a, b) => a - b},
            {"/", (a, b) => a / b},
            {"*", (a, b) => a * b}
        };

        public static void Main(string[] args)
        {
            try
            {
                System.Console.WriteLine("Enter input for RPN: ");
                string input = System.Console.ReadLine()!;
                int res = new RPNCalculator().Calculate(input.Split(' ', StringSplitOptions.RemoveEmptyEntries));
                System.Console.WriteLine(res);
            }
            catch (InvalidOperationException ex)
            {
                System.Console.WriteLine(ex.Message);
            }

        }

        private int Calculate(string[] tokens)
        {
            foreach (string token in tokens)
            {
                if (operators.ContainsKey(token))
                {
                    int b = memory.Pop();
                    int a = memory.Pop();
                    int res = Apply(op: token, a, b);
                    memory.Push(res);
                    continue;
                }

                if (int.TryParse(token, out var intVal))
                {
                    memory.Push(intVal);
                }
                else
                {
                    throw new InvalidOperationException($"'{token}' is not a digit or an operation[+,-,*,/]");
                }
            }
            return memory.Pop();
        }

        private int Apply(string op, int a, int b)
        {
            return operators[op](a, b);
        }
    }
}