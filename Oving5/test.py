import io, sys
program = 'print("Hore")'
old_stdout = sys.stdout 
new_stdout = io.StringIO() 
sys.stdout = new_stdout
exec(program)
result = sys.stdout.getvalue().strip()
sys.stdout = old_stdout 
tekst = str(result)
i = 8



