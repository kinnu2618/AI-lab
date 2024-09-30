
import numpy as np

def n_queens(n):
  """
  Return the number of solutions to the n-queens problem.

  Args:
    n: The number of queens to place.

  Returns:
    The number of solutions.
  """

  # Create a 2D array to represent the chessboard.
  board = np.zeros((n, n))

  # Place the first queen on the first row.
  board[0, 0] = 1

  # Recursively place the remaining queens.
  return _n_queens(board, 1, n)

def _n_queens(board, row, n):
  """
  Recursively place the remaining queens on the chessboard.

  Args:
    board: The current state of the chessboard.
    row: The current row to place a queen on.
    n: The number of queens to place.

  Returns:
    The number of solutions.
  """

  # Check if all queens have been placed.
  if row == n:
    return 1

  # Count the number of solutions for each column in the current row.
  solutions = 0
  for col in range(n):
    # Check if the queen can be placed in the current column.
    if is_safe(board, row, col):
      # Place the queen in the current column.
      board[row, col] = 1

      # Recursively place the remaining queens.
      solutions += _n_queens(board, row + 1, n)

      # Remove the queen from the current column.
      board[row, col] = 0

  return solutions

def is_safe(board, row, col):
  """
  Check if a queen can be placed on the given square.

  Args:
    board: The current state of the chessboard.
    row: The row of the square.
    col: The column of the square.

  Returns:
    True if the queen can be placed, False otherwise.
  """

  # Check if there is a queen in the same row.
  for i in range(col):
    if board[row, i] == 1:
      return False

  # Check if there is a queen in the same column.
  for i in range(row):
    if board[i, col] == 1:
      return False

  # Check if there is a queen in the same diagonal.
  i, j = row, col
  while i >= 0 and j >= 0:
    if board[i, j] == 1:
      return False
    i -= 1
    j -= 1

  i, j = row, col
  while i < n and j >= 0:
    if board[i, j] == 1:
      return False
    i += 1
    j -= 1

  return True
